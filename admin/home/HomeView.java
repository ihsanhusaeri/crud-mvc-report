package projectsim.admin.home;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
//import projectsim.admin.login.*;
import projectsim.admin.perusahaan.view.*;
import projectsim.admin.pelamar.view.*;
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
	private JPanel panelPelamar = new JPanel();
	private JPanel panelPerusahaan = new JPanel();
	private JPanel centerPanel = new JPanel();
	private JPanelSlider panelSlider = new JPanelSlider();
	private JPanel perusahaanView = new PerusahaanView();
	private JPanel pelamarView = new PelamarView();
	public HomeView(){
		super("Home");
		
		setLayout(new BorderLayout());
		setSize(1200, 700);
		//setResizable(false);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		leftPanel.setLayout(new BorderLayout());
		
		leftPanelTop.setBackground(Color.decode("#25704a"));
		leftPanelLeft.setBackground(Color.decode("#25704a"));
		leftPanelRight.setBackground(Color.decode("#25704a"));
		leftPanelBottom.setBackground(Color.decode("#25704a"));
		leftPanelCenter.setBackground(Color.decode("#25704a"));
		
		leftPanelCenter.setLayout(new GridLayout(15, 1, 0, 20));
		
		//leftPanel.add("South", leftPanelBottom);
		//leftPanel.add("North", leftPanelTop);
		//leftPanel.add("West", leftPanelLeft);
		//leftPanel.add("East", leftPanelRight);
		leftPanel.add("Center", leftPanelCenter);
		
		panelHome.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelHome.add(makeJLabel("                  Beranda                   "));
		panelHome.setBackground(Color.decode("#2caa6b"));
		
		panelPerusahaan.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelPerusahaan.add(makeJLabel("                  Perusahaan                   "));
		panelPerusahaan.setBackground(Color.decode("#2caa6b"));
		
		panelPelamar.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelPelamar.add(makeJLabel("Pelamar"));
		panelPelamar.setBackground(Color.decode("#2caa6b"));
		
		leftPanelCenter.add(new JLabel("                     "));
		leftPanelCenter.add(panelHome);
		leftPanelCenter.add(panelPerusahaan);
		leftPanelCenter.add(panelPelamar);
		Border bdr = BorderFactory.createEtchedBorder(); 
		Border titlebdr = BorderFactory.createTitledBorder(bdr ,"Menu");
		leftPanelCenter.setBorder(titlebdr);
		
		centerPanel.setBackground(Color.WHITE);
		centerPanel.setLayout(new BorderLayout());
		
		panelPerusahaan.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent r){
				panelSlider.nextPanel(10, perusahaanView, panelSlider.right);
			}
		});
		panelPelamar.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent r){
				panelSlider.nextPanel(10, pelamarView, panelSlider.left);
			}
		});
		
		panelHomeCenter.add(new JLabel("Ihsan Husa"));
		panelHomeCenter.setBackground(Color.WHITE);
		
		
		panelSlider.add(panelHomeCenter);
		panelSlider.add(perusahaanView);
		panelSlider.add(pelamarView);
		
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
		/*try{
			UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
		}catch(Exception e){
			e.printStackTrace();
		}*/
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
