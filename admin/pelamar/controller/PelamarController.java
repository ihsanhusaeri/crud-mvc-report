package projectsim.admin.pelamar.controller;

import projectsim.admin.pelamar.view.*;
import projectsim.admin.pelamar.service.*;
import projectsim.admin.pelamar.service.impl.*;
import projectsim.admin.pelamar.model.*;
import projectsim.connection.*;
import projectsim.error.*;
import java.util.*;
import java.text.*;
import javax.swing.*;
import java.io.*;
public class PelamarController{
	private PelamarView pelamarView;
	private PelamarDao pelamarDao;
	private Pelamar p;
	private List<Pelamar> listPelamar;
	private InputStream image;
	public PelamarController(PelamarView pelamarView){
		this.pelamarView = pelamarView;
		pelamarDao = new PelamarDaoImpl();
	}
	public void save(){
		p = new Pelamar();
		String path = pelamarView.getTxtFoto().getText();
		
		try{
			p.setName(pelamarView.getTxtNama().getText());
			p.setTTL(pelamarView.getComboCalendar().getSelectedItem().toString());
			p.setAlamat(pelamarView.getTxtAlamat().getText());
			p.setNoKtp(pelamarView.getTxtNoKtp().getText());
			p.setJenisK(pelamarView.getComboJenisKelamin().getSelectedItem().toString());
			p.setPendidikan(pelamarView.getComboPendidikan().getSelectedItem().toString());
			p.setSuratKesehatan(new FileInputStream(new File(path)));
			p.setTahun(pelamarView.getTahun());
			
			pelamarDao.save(p);
			viewAllData();
			resetField();
			JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan", "Simpan Data", JOptionPane.INFORMATION_MESSAGE);
		}catch(FileNotFoundException esx){
			esx.printStackTrace();
		}catch(DisnakerException e){
			e.printStackTrace();
		}
	}
	public void edit(){
		p = new Pelamar();
		try{
			String path = pelamarView.getTxtFoto().getText();
			p.setId(Integer.parseInt(pelamarView.getTxtID().getText()));
			p.setName(pelamarView.getTxtNama().getText());
			p.setTTL(pelamarView.getComboCalendar().getSelectedItem().toString());
			p.setAlamat(pelamarView.getTxtAlamat().getText());
			p.setNoKtp(pelamarView.getTxtNoKtp().getText());
			p.setJenisK(pelamarView.getComboJenisKelamin().getSelectedItem().toString());
			p.setPendidikan(pelamarView.getComboPendidikan().getSelectedItem().toString());
			//p.setSuratKesehatan(new FileInputStream(new File(path)));	
			pelamarDao.edit(p);
			JOptionPane.showMessageDialog(null, "Berhasil Mengubah!", "Edit Data", JOptionPane.INFORMATION_MESSAGE);
			//pelamarView.getTxtID().setEnabled(true);
			viewAllData();
			resetField();
		}/*catch(FileNotFoundException esx){
			esx.printStackTrace();
		}*/catch(DisnakerException e){
			e.printStackTrace();
		}
	}
	public void viewData(){
		try{
			String kategoriCari = pelamarView.getComboKategoriCari().getSelectedItem().toString();
			listPelamar = pelamarDao.getPelamar(pelamarView.getTxtCari().getText(), kategoriCari);
			TableModel tmb = new TableModel(listPelamar);
			pelamarView.getTableData().setModel(tmb);
			JOptionPane.showMessageDialog(null, "Data ditemukan", "Cari Data", JOptionPane.INFORMATION_MESSAGE);
		}catch(DisnakerException r){
			JOptionPane.showMessageDialog(null, r.getMessage(), "Cari Data", JOptionPane.WARNING_MESSAGE);
			
		}
	}
	public void delete(){
		try{
			pelamarDao.delete(Integer.parseInt(pelamarView.getTxtID().getText()));
			JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus", "Hapus Data", JOptionPane.INFORMATION_MESSAGE);
			viewAllData();
			resetField();
		}catch(DisnakerException e){
			e.printStackTrace();
		}
	}
	public void viewAllData(){
		try{
			listPelamar = pelamarDao.getAllData();
			TableModel tmb = new TableModel(listPelamar);
			pelamarView.getTableData().setModel(tmb);
		}catch(DisnakerException e){
			e.printStackTrace();
		}
	}
	
	public void fillField(int row){
		pelamarView.getTxtID().setText(String.valueOf(listPelamar.get(row).getId()));
		pelamarView.getTxtNama().setText(listPelamar.get(row).getName());
		pelamarView.getTxtAlamat().setText(listPelamar.get(row).getAlamat());
		pelamarView.getComboCalendar().setSelectedItem(listPelamar.get(row).getTTL());
		pelamarView.getTxtNoKtp().setText(listPelamar.get(row).getNoKtp());
		pelamarView.getComboPendidikan().setSelectedItem(listPelamar.get(row).getPendidikan());
		pelamarView.getComboJenisKelamin().setSelectedItem(listPelamar.get(row).getJenisK());
		
		image = listPelamar.get(row).getSuratKesehatan();
	}
	
	public void resetField(){
		createId();
		pelamarView.getTxtNama().setText("");
		pelamarView.getTxtAlamat().setText("");
		pelamarView.getTxtFoto().setText("");
		Date d = new Date();
		String now = new SimpleDateFormat("MMMM dd, yyyy").format(d).toString();
		pelamarView.getComboCalendar().setSelectedItem(now);
		pelamarView.getTxtNoKtp().setText("");
		pelamarView.getComboJenisKelamin().setSelectedItem("Jenis Kelamin");
		pelamarView.getComboPendidikan().setSelectedItem("Pendidikan");
	}
	public void showSertifikat(){
		Upload upload = new Upload(pelamarView, image);
		upload.setVisible(true);
	}
	public void createId(){
		try{
			int id = pelamarDao.createId();
			pelamarView.getTxtID().setText(String.valueOf(id));
		}catch(DisnakerException er){
			er.printStackTrace();
		}
	}
	
}