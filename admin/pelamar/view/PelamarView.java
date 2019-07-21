package projectsim.admin.pelamar.view;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.text.*;
import java.awt.event.*;
import org.freixas.jcalendar.*;
import projectsim.admin.pelamar.model.*;
import projectsim.admin.pelamar.controller.*;

public class PelamarView extends JPanel{
	private JTextField tNoId = new JTextField();
	private JTextField tNama = new JTextField();
	private JTextField tNoKtp = new JTextField();
	private JTextArea tAlamat = new JTextArea();
	private String[] pendidikan = {"Pendidikan", "S3", "S2", "S1", "SMA", "SMP", "SD"};
	private JComboBox cPendidikan = new JComboBox(pendidikan);
	private JCalendarCombo cTTL = new JCalendarCombo();
	private JPanel leftPanel = new JPanel();
	private JPanel rightPanel = new JPanel();
	private JPanel topPanel = new JPanel();
	private JPanel bottomPanel = new JPanel();
	private JPanel centerPanel = new JPanel();
	private JButton bSimpan = new JButton("     Simpan    ");
	private JButton bEdit = new JButton("        Edit        ");
	private JButton bHapus	= new JButton("      Hapus     ");
	private JPanel centerPanelNorth = new JPanel();
	private JPanel centerPanelNorthTop = new JPanel();
	private JPanel centerPanelNorthCenter = new JPanel();
	private JPanel centerPanelNorthBottom = new JPanel();
	private JPanel centerPanelCenter = new JPanel();
	private JTextField tId = new JTextField();
	private JTextField tCari = new JTextField(13);
	private JLabel judul = new JLabel("Data Pelamar");
	private JTextField tGambar = new JTextField(10);
	private String[] jenisK = {"Jenis Kelamin", "Laki-Laki", "Perempuan"};
	private JComboBox cJenisK = new JComboBox(jenisK);
	private JButton bPilih = new JButton("Pilih");
	private JButton bLihat = new JButton("Lihat Surat");
	private String[] kategoriCari = {"id", "nama", "alamat", "kategori"};
	private JComboBox cKategoriCari = new JComboBox(kategoriCari);
	private JButton bCari = new JButton("Cari");
	private TableModel tmb;
	private JScrollPane scroller;
	private JTable tabel;
	private Upload upload = new Upload(this);
	private PelamarController pelamarController;
	public PelamarView(){
		
		pelamarController = new PelamarController(this);
		
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
		centerPanelNorthBottom.add(new JLabel("Cari Berdasarkan : "));
		centerPanelNorthBottom.add(cKategoriCari);
		centerPanelNorthBottom.add(tCari);
		tNoId.setEnabled(false);
		centerPanelNorthBottom.add(bCari);
		centerPanelNorthBottom.add(bLihat);
		bLihat.setVisible(false);
		
		centerPanelCenter.setLayout(new BorderLayout());
		tabel = new JTable();
		scroller = new JScrollPane();
		scroller.getViewport().add(tabel, null);
		scroller.getViewport().setBackground(Color.decode("#c9fccf"));
		tmb = new TableModel();
		tabel.setModel(tmb);
		centerPanelCenter.add("Center",scroller);
		
		centerPanelCenter.setBackground(Color.decode("#c9fccf"));
		centerPanelNorthCenter.setBackground(Color.decode("#c9fccf"));
		centerPanelNorthTop.setBackground(Color.decode("#c9fccf"));
		centerPanelNorthBottom.setBackground(Color.decode("#c9fccf"));
		
		centerPanelNorth.setLayout(new BorderLayout(2,1));
		centerPanelNorth.add("North", centerPanelNorthTop);
		centerPanelNorth.add("Center", centerPanelNorthCenter);
		centerPanelNorth.add("South", centerPanelNorthBottom);
		
		centerPanel.add("Center", centerPanelCenter);
		centerPanel.add("North", centerPanelNorth);
		
		bPilih.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				upload.setVisible(true);
			}
		});
		bSimpan.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				pelamarController.save();
			}
		});
		bEdit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				pelamarController.edit();
			}
		});
		bHapus.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				pelamarController.delete();
			}
		});
		bCari.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				pelamarController.viewData();
			}
		});
		bLihat.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				pelamarController.showSertifikat();
			}
		});
		
		pelamarController.viewAllData();
		tabel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				int row= tabel.getSelectedRow();
				pelamarController.fillField(row);
				bLihat.setVisible(true);
				
			}
		});
		
		add("West", leftPanel);
		add("East", rightPanel);
		add("South", bottomPanel);
		add("North", topPanel);
		add("Center",centerPanel);
		pelamarController.createId();
	}
	public JPanel field1(){
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(6, 2, 0, 20));
		p.setBackground(Color.decode("#c9fccf"));
		p.add(new JLabel(""));
		p.add(new JLabel(""));
		p.add(new JLabel("No ID"));
		p.add(tNoId);
		p.add(new JLabel("Nama"));
		p.add(tNama);
		p.add(new JLabel("Tanggal Lahir"));
		p.add(cTTL);
		cTTL.setDateFormat(new SimpleDateFormat("dd MMMM yyyy"));
		p.add(new JLabel("Jenis Kelamin"));
		p.add(cJenisK);		
		
		return p;
	}
	public JPanel field2(){
		JPanel p = new JPanel();
		JPanel panelImage = new JPanel();
		panelImage.add(tGambar);
		panelImage.add(bPilih);
		panelImage.setLayout(new FlowLayout(FlowLayout.LEFT, 5,5));
		p.setLayout(new GridLayout(6,2,0,20));
		p.setBackground(Color.decode("#c9fccf"));
		panelImage.setBackground(Color.decode("#c9fccf"));
		
		p.add(new JLabel(""));
		p.add(new JLabel(""));
		p.add(new JLabel("Pendidikan"));
		p.add(cPendidikan);
		p.add(new JLabel("Alamat"));
		p.add(new JScrollPane(tAlamat));
		p.add(new JLabel("No. KTP"));
		p.add(tNoKtp);
		p.add(new JLabel("Surat Kesehatan"));
		p.add(panelImage);
		p.add(new JLabel(""));
		p.add(new JLabel(""));
		
		return p;
	}
	public JTextField getTxtFoto(){
		return tGambar;
	}
	public JTextField getTxtID(){
		return tNoId;
	}
	public JTextField getTxtNama(){
		return tNama;
	}
	public JTextArea getTxtAlamat(){
		return tAlamat;
	}
	public JTextField getTxtNoKtp(){
		return tNoKtp;
	}
	public JTextField getTxtCari(){
		return tCari;
	}
	public JComboBox getComboPendidikan(){
		return cPendidikan;
	}
	public JComboBox getComboJenisKelamin(){
		return cJenisK;
	}
	public JComboBox getComboKategoriCari(){
		return cKategoriCari;
	}
	public JCalendarCombo getComboCalendar(){
		return cTTL;
	}
	public JTable getTableData(){
		return tabel;
	}
	public String getTahun(){
		Date d = new Date();
		String format = new SimpleDateFormat("yyyy").format(d).toString();
		return format;
	}
	
	
}