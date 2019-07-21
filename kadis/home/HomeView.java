package projectsim.kadis.home;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import projectsim.kadis.laporan.view.*;
import projectsim.kadis.grafik.view.*;
import diu.swe.habib.JPanelSlider.JPanelSlider;
public class HomeView extends JFrame{
	private JPanel leftPanel = new JPanel();
	private JPanel leftPanelCenter = new JPanel();
	private JPanel leftPanelRight = new JPanel();
	private JPanel leftPanelLeft = new JPanel();
	private JPanel leftPanelTop = new JPanel();
	private JPanel leftPanelBottom = new JPanel();
	private JPanel panelHome = new JPanel();
	private JPanel panelHomeCenter = new JPanel();
	private JPanel panelGrafik = new JPanel();
	private JPanel panelLaporan = new JPanel();
	private JPanel centerPanel = new JPanel();
	private JPanelSlider panelSlider = new JPanelSlider();
	private LaporanView laporanView = new LaporanView();
	private GrafikView grafikView = new GrafikView();
	public HomeView(){
		super("Home");
		
		setLayout(new BorderLayout());
		setSize(1100, 700);
		//setResizable(false);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		leftPanel.setLayout(new BorderLayout());
		
		leftPanelTop.setBackground(Color.decode("#25704a"));
		leftPanelLeft.setBackground(Color.decode("#25704a"));
		leftPanelRight.setBackground(Color.decode("#25704a"));
		leftPanelBottom.setBackground(Color.decode("#25704a"));
		leftPanelCenter.setBackground(Color.decode("#25704a"));
		
		leftPanelCenter.setLayout(new GridLayout(15, 1, 0, 20));
		
		leftPanel.add("Center", leftPanelCenter);
		
		panelHome.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelHome.add(makeJLabel("                  Beranda                   "));
		panelHome.setBackground(Color.decode("#2caa6b"));
		
		panelLaporan.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelLaporan.add(makeJLabel("                  Laporan                   "));
		panelLaporan.setBackground(Color.decode("#2caa6b"));
		
		panelGrafik.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelGrafik.add(makeJLabel("Grafik"));
		panelGrafik.setBackground(Color.decode("#2caa6b"));
		
		leftPanelCenter.add(new JLabel("                     "));
		leftPanelCenter.add(panelHome);
		leftPanelCenter.add(panelLaporan);
		leftPanelCenter.add(panelGrafik);
		Border bdr = BorderFactory.createEtchedBorder(); 
		Border titlebdr = BorderFactory.createTitledBorder(bdr ,"Menu");
		leftPanelCenter.setBorder(titlebdr);
		
		centerPanel.setBackground(Color.WHITE);
		centerPanel.setLayout(new BorderLayout());
		
		panelLaporan.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent r){
				panelSlider.nextPanel(10, laporanView, panelSlider.right);
			}
		});
		panelGrafik.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent r){
				panelSlider.nextPanel(10, grafikView, panelSlider.right);
			}
		});
		panelGrafik.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent r){
				//panelSlider.nextPanel(10, pelamarView, panelSlider.left);
			}
		});
		
		panelHomeCenter.add(new JLabel("Ihsan Husa"));
		panelHomeCenter.setBackground(Color.WHITE);
		
		
		panelSlider.add(panelHomeCenter);
		panelSlider.add(laporanView);
		panelSlider.add(grafikView);
		
		centerPanel.add("Center", panelSlider);
		
		
		
		add("West", leftPanel);
		add("Center", centerPanel);
	}
	public JLabel makeJLabel(String label){
		JLabel l = new JLabel(label);
		l.setForeground(Color.WHITE);
		l.setFont(new Font("Serif", Font.BOLD, 13));
		return l;
	}
	public static void main(String[] args){
		try{
				com.jtattoo.plaf.acryl.AcrylLookAndFeel.setTheme("Green", "INSERT YOUR LICENCE KEY HERE", "");
			try{
				UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
			}catch(Exception er){
				er.printStackTrace();
			}
		}catch(Exception er){
				er.printStackTrace();
		}
		HomeView home = new HomeView();
		home.setVisible(true);
	}
	
}
