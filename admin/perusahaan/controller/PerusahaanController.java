package projectsim.admin.perusahaan.controller;

import projectsim.admin.perusahaan.service.*;
import projectsim.admin.perusahaan.service.impl.*;
import projectsim.admin.perusahaan.view.*;
import projectsim.admin.perusahaan.model.*;
import projectsim.connection.*;
import projectsim.error.*;
import java.sql.*;
import java.util.*;
import java.util.Date;
import javax.swing.*;
import java.text.*;

public class PerusahaanController{
	private PerusahaanView perusahaanView;
	private PerusahaanDao perusahaanDao;
	private Perusahaan p;
	private List<Perusahaan> perusahaans;
	public PerusahaanController(PerusahaanView perusahaanView){
		this.perusahaanView = perusahaanView;
		perusahaanDao = new PerusahaanDaoImpl();
	}
	public void save(){
		p = new Perusahaan();
		p.setId(perusahaanView.getTxtID().getText());
		p.setKodePerusahaan(perusahaanView.getTxtKodePerusahaan().getText());
		p.setName(perusahaanView.getTxtName().getText());
		p.setAlamat(perusahaanView.getTxtAlamat().getText());
		p.setTanggalBerdiri(perusahaanView.getComboCalendar().getSelectedItem().toString());
		p.setTahun(perusahaanView.getTahun());
		p.setKuotaKaryawan(Integer.parseInt(perusahaanView.getTxtKuotaKaryawan().getText()));
		p.setJumlahKaryawan(Integer.parseInt(perusahaanView.getTxtJumlahKaryawan().getText()));
		p.setKategori(perusahaanView.getComboKategori().getSelectedItem().toString());
		p.setStatus(perusahaanView.getComboStatus().getSelectedItem().toString());
		try{
			perusahaanDao.save(p);
			JOptionPane.showMessageDialog(null, "Berhasil Menyimpan!", "Simpan Data", JOptionPane.INFORMATION_MESSAGE);
			viewAllData();
			resetField();
		}catch(DisnakerException e){
			e.printStackTrace();
		}
	}
	public void edit(){
		p = new Perusahaan();
		p.setId(perusahaanView.getTxtID().getText());
		p.setKodePerusahaan(perusahaanView.getTxtKodePerusahaan().getText());
		p.setName(perusahaanView.getTxtName().getText());
		p.setAlamat(perusahaanView.getTxtAlamat().getText());
		p.setTanggalBerdiri(perusahaanView.getComboCalendar().getSelectedItem().toString());
		p.setTahun(perusahaanView.getTahun());
		p.setKuotaKaryawan(Integer.parseInt(perusahaanView.getTxtKuotaKaryawan().getText()));
		p.setJumlahKaryawan(Integer.parseInt(perusahaanView.getTxtJumlahKaryawan().getText()));
		p.setKategori(perusahaanView.getComboKategori().getSelectedItem().toString());
		p.setStatus(perusahaanView.getComboStatus().getSelectedItem().toString());
		
		try{
			perusahaanDao.edit(p);
			JOptionPane.showMessageDialog(null, "Berhasil Mengubah!", "Edit Data", JOptionPane.INFORMATION_MESSAGE);
			viewAllData();
			resetField();
		}catch(DisnakerException e){
			e.printStackTrace();
		}
	}
	public void delete(){
		int ok = JOptionPane.showConfirmDialog(null, "Anda yakin akan menghapus data ini?", "Hapus Data", JOptionPane.YES_NO_OPTION);
		if(ok == 0){
			try{
				perusahaanDao.delete(perusahaanView.getTxtID().getText());
				perusahaanView.getTxtID().setEnabled(true);
				viewAllData();
				resetField();
			}catch(DisnakerException e){
				e.printStackTrace();
			}
		}
	}
	public void viewData(){
		try{
			String kategoriCari = perusahaanView.getComboKategoriCari().getSelectedItem().toString();
			perusahaans = perusahaanDao.getPerusahaan(perusahaanView.getTxtCari().getText(), kategoriCari);
			TableModelPerusahaan tmb = new TableModelPerusahaan(perusahaans);
			perusahaanView.getTableData().setModel(tmb);
			JOptionPane.showMessageDialog(null, "Data ditemukan", "Cari Data", JOptionPane.INFORMATION_MESSAGE);
		}catch(DisnakerException r){
			JOptionPane.showMessageDialog(null, r.getMessage(), "Cari Data", JOptionPane.WARNING_MESSAGE);
			
		}
	}
	public void viewAllData(){
		try{
			perusahaans = perusahaanDao.getAllData();
			TableModelPerusahaan tmb = new TableModelPerusahaan(perusahaans);
			perusahaanView.getTableData().setModel(tmb);
		}catch(DisnakerException e){
			e.printStackTrace();
		}
	}
	public void resetField(){
		createId();
		perusahaanView.getTxtName().setText("");
		perusahaanView.getTxtKodePerusahaan().setText("");
		perusahaanView.getTxtAlamat().setText("");
		Date d = new Date();
		String now = new SimpleDateFormat("EEEE, MMMM dd, yyyy").format(d).toString();
		perusahaanView.getComboCalendar().setSelectedItem(now);
		perusahaanView.getTxtKuotaKaryawan().setText("");
		perusahaanView.getTxtJumlahKaryawan().setText("");
		perusahaanView.getComboKategori().setSelectedItem("Kategori");
	}
	public void fillField(int row){
		perusahaanView.getTxtID().setText(perusahaans.get(row).getId());
		perusahaanView.getTxtName().setText(perusahaans.get(row).getName());
		perusahaanView.getTxtKodePerusahaan().setText(perusahaans.get(row).getKodePerusahaan());
		perusahaanView.getTxtAlamat().setText(perusahaans.get(row).getAlamat());
		perusahaanView.getComboCalendar().setSelectedItem(perusahaans.get(row).getTanggalBerdiri());
		perusahaanView.getTxtKuotaKaryawan().setText(String.valueOf(perusahaans.get(row).getKuotaKaryawan()));
		perusahaanView.getTxtJumlahKaryawan().setText(String.valueOf(perusahaans.get(row).getJumlahKaryawan()));
		perusahaanView.getComboKategori().setSelectedItem(perusahaans.get(row).getKategori());
		perusahaanView.getComboKategori().setSelectedItem(perusahaans.get(row).getStatus());
	}
	public void createId(){
		try{
			int id = perusahaanDao.createId();
			perusahaanView.getTxtID().setText(String.valueOf(id));
		}catch(DisnakerException er){
			er.printStackTrace();
		}
	}
}
