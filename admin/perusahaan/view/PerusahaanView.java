package projectsim.admin.perusahaan.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import projectsim.admin.perusahaan.model.*;
import projectsim.admin.perusahaan.controller.*;
import java.util.*;
import java.text.*;
import org.freixas.jcalendar.*;

public class PerusahaanView extends JPanel{
	private JPanel leftPanel = new JPanel();
	private JPanel rightPanel = new JPanel();
	private JPanel topPanel = new JPanel();
	private JPanel bottomPanel = new JPanel();
	private JPanel centerPanel = new JPanel();
	private JPanel centerPanelNorth = new JPanel();
	private JPanel centerPanelNorthTop = new JPanel();
	private JPanel centerPanelNorthCenter = new JPanel();
	private JPanel centerPanelNorthBottom = new JPanel();
	private JPanel centerPanelCenter = new JPanel();
	private JTextField tId = new JTextField();
	private JTextField tKode = new JTextField();
	private JTextField tNama = new JTextField();
	private JTextField tCari = new JTextField(13);
	private JTextField tJumlahKaryawan = new JTextField();
	private JTextField tAlokasiKaryawan = new JTextField();
	private JLabel judul = new JLabel("Data Perusahaan");
	private String kategori[] = {"Kategori","Otomotif", "Elektronik"};
	private JComboBox cKategori = new JComboBox(kategori);
	private String status[] = {"Tersedia", "Tidak Tersedia"};
	private JComboBox cStatus = new JComboBox(status);
	private JTextArea tAlamat = new JTextArea();
	private JButton bSimpan = new JButton("     Simpan    ");
	private JButton bEdit = new JButton("        Edit        ");
	private JButton bHapus	= new JButton("      Hapus     ");
	private JButton bCari	= new JButton("Cari");
	private TableModel tmb;
	private JTable tabel;
	private String[] kategoriCari = {"id", "nama", "kategori"};
	private JComboBox cKategoriCari = new JComboBox(kategoriCari);
	private JScrollPane scroller;
	private JCalendarCombo chooser = new JCalendarCombo();
	private PerusahaanController perusahaanController;
	
	public PerusahaanView(){
		perusahaanController= new PerusahaanController(this);
		tabel = new JTable();
		scroller = new JScrollPane();
		scroller.getViewport().add(tabel, null);
		scroller.getViewport().setBackground(Color.decode("#c9fccf"));
		
		perusahaanController.viewAllData();
		setLayout(new BorderLayout());
		leftPanel.setBackground(Color.WHITE);
		rightPanel.setBackground(Color.WHITE);
		topPanel.setBackground(Color.WHITE);
		bottomPanel.setBackground(Color.WHITE);
		centerPanel.setBackground(Color.WHITE);
		
		centerPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#c9fccf")));
		centerPanel.setLayout(new BorderLayout());
		
		centerPanelNorthTop.setLayout(new FlowLayout(FlowLayout.CENTER));
		judul.setFont(new Font("Serif", Font.BOLD, 14));
		centerPanelNorthTop.add(judul);
		
		centerPanelNorthCenter.setLayout(new GridLayout(1, 2, 30,5));
		centerPanelNorthCenter.add(field1());
		centerPanelNorthCenter.add(field2());
		
		centerPanelNorthBottom.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
		centerPanelNorthBottom.add(bSimpan);
		centerPanelNorthBottom.add(bEdit);
		centerPanelNorthBottom.add(bHapus);
		centerPanelNorthBottom.add(new JLabel("                   "));
		centerPanelNorthBottom.add(new JLabel("                   "));
		centerPanelNorthBottom.add(new JLabel("Cari Berdasarkan : "));
		centerPanelNorthBottom.add(cKategoriCari);
		centerPanelNorthBottom.add(tCari);
		centerPanelNorthBottom.add(bCari);
		
		
		
		centerPanelNorth.setLayout(new BorderLayout(2,1));
		centerPanelNorth.add("North", centerPanelNorthTop);
		centerPanelNorth.add("Center", centerPanelNorthCenter);
		centerPanelNorth.add("South", centerPanelNorthBottom);
		
		centerPanelCenter.setLayout(new BorderLayout());
		
	
		centerPanelCenter.add("Center",scroller);
		
	
		centerPanelCenter.setBackground(Color.decode("#c9fccf"));
		centerPanelNorthCenter.setBackground(Color.decode("#c9fccf"));
		centerPanelNorthTop.setBackground(Color.decode("#c9fccf"));
		centerPanelNorthBottom.setBackground(Color.decode("#c9fccf"));
		
		centerPanel.add("Center", centerPanelCenter);
		centerPanel.add("North", centerPanelNorth);
		
		
		add("West", leftPanel);
		add("East", rightPanel);
		add("South", bottomPanel);
		add("North", topPanel);
		add("Center",centerPanel);
		
		bSimpan.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				perusahaanController.save();
			}
		});
		bEdit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				perusahaanController.edit();
			}
		});
		bHapus.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				perusahaanController.delete();
			}
		});
		bCari.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				perusahaanController.viewData();
			}
		});
		tabel.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent evt){
				int row = tabel.getSelectedRow();
				perusahaanController.fillField(row);
				tId.setEnabled(false);
			}
		});
		perusahaanController.createId();

	}
	public JPanel field1(){
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(6, 2, 0, 20));
		p.setBackground(Color.decode("#c9fccf"));
		p.add(new JLabel(""));
		p.add(new JLabel(""));
		p.add(new JLabel("Kode Pendaftaran"));
		p.add(tId);
		tId.setEnabled(false);
		p.add(new JLabel("Kode Perusahaan"));
		p.add(tKode);
		p.add(new JLabel("Nama"));
		p.add(tNama);
		p.add(new JLabel("Alamat"));
		p.add(new JScrollPane(tAlamat));
		p.add(new JLabel("Kategori"));
		p.add(cKategori);
		
		return p;
		
	}
	public JPanel field2(){
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(6,2,0,20));
		p.setBackground(Color.decode("#c9fccf"));
		p.add(new JLabel(""));
		p.add(new JLabel(""));
		p.add(new JLabel("Tanggal Berdiri"));
		chooser.setDateFormat(new SimpleDateFormat("dd MMM yyyy"));
		p.add(chooser);
		p.add(new JLabel("Jumlah Karyawan"));
		p.add(tJumlahKaryawan);
		p.add(new JLabel("Kuota Karyawan Baru"));
		p.add(tAlokasiKaryawan);
		p.add(new JLabel("Keterangan"));
		p.add(cStatus);
		p.add(new JLabel(""));
		p.add(new JLabel(""));
		
		return p;
	}
	public JTextField getTxtID(){
		return tId;
	}
	public JTextField getTxtKodePerusahaan(){
		return tKode;
	}
	public JTextField getTxtName(){
		return tNama;
	}
	public JTextField getTxtJumlahKaryawan(){
		return tJumlahKaryawan;
	}
	public JTextField getTxtCari(){
		return tCari;
	}
	public JTextField getTxtKuotaKaryawan(){
		return tAlokasiKaryawan;
	}
	public JTextArea getTxtAlamat(){
		return tAlamat;
	}
	public JCalendarCombo getComboCalendar(){
		return chooser;
	}
	public JTable getTableData(){
		return tabel;
	}
	public JComboBox getComboStatus(){
		return cStatus;
	}
	public JComboBox getComboKategori(){
		return cKategori;
	}
	public JComboBox getComboKategoriCari(){
		return cKategoriCari;
	}
	public String getTahun(){
		Date d = new Date();
		String format = new SimpleDateFormat("yyyy").format(d).toString();
		return format;
	}
	
}