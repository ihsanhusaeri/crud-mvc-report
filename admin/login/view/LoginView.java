package projectsim.admin.login.view;

import projectsim.admin.login.controller.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
public class LoginView extends JFrame{
	private JButton bLogin;
	private JButton bCancel;
	private JTextField tName;
	private JPasswordField tPass;
	private JPanel panelKiri;
	private JPanel panelKanan;
	private JPanel panelLogin;
	private JPanel panelLoginCenter;
	private JPanel panelLoginSouth;
	private Border border;
	private LoginController loginController;
	public LoginView(){
		super("Admin");
		setSize(400, 300);
		setResizable(false);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(1,2));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		loginController = new LoginController(this);
		bLogin = new JButton("Login");
		bCancel = new JButton("Cancel");
		
		panelKanan = new JPanel();
		panelKanan.setBackground(Color.WHITE);
		panelKanan.setLayout(new BorderLayout());
		panelKanan.add("North", new JPanel());
		panelKanan.add("South", new JPanel());
		panelKanan.add("West", new JPanel());
		panelKanan.add("East", new JPanel());
		
		panelLogin = new JPanel();
		panelLogin.setLayout(new BorderLayout());
		Border bdr = BorderFactory.createEtchedBorder(); 
		Border titlebdr = BorderFactory.createTitledBorder(bdr ,"Login Admin");
		panelLogin.setBorder(titlebdr);
		//panelLogin.add(new JLabel(""));
		panelLogin.add("West",new JPanel());
		panelLogin.add("North",new JPanel());
		panelLogin.add("East",new JPanel());
		//panelLogin.add("South",new JPanel());
		
		
		
		tName = new JTextField();
		tPass = new JPasswordField();
		panelLoginCenter = new JPanel();
		panelLoginCenter.setLayout(new GridLayout(5,2, 10,10));
		panelLoginCenter.add(new JLabel(""));
		panelLoginCenter.add(new JLabel(""));
		panelLoginCenter.add(new JLabel("Username : "));
		panelLoginCenter.add(tName);
		panelLoginCenter.add(new JLabel("Password : "));
		panelLoginCenter.add(tPass);
		//panelLoginCenter.add(new JLabel("tiga"));
		//panelLoginCenter.add(new JLabel("empat"));
		
		
		bLogin = new JButton("Login", new ImageIcon("D:\\ProjectJava\\Icon\\Masuk.png"));
		bCancel = new JButton("Cancel", new ImageIcon(  "D:\\ProjectJava\\Icon\\Cancel.png"));
		panelLoginSouth = new JPanel();
		panelLoginSouth.setLayout(new FlowLayout(FlowLayout.CENTER, 10,10));
		panelLoginSouth.add(bLogin);
		panelLoginSouth.add(bCancel);
		panelLoginSouth.setBorder(BorderFactory.createEtchedBorder());
		
		panelLogin.add("Center",panelLoginCenter);
		panelLogin.add("South",panelLoginSouth);
		
		panelKanan.add("Center",panelLogin);
		
		add(panelKanan);
		bLogin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				setCursor(Cursor.WAIT_CURSOR);
				loginController.validasiAdmin();
				setCursor(Cursor.DEFAULT_CURSOR);
			}
		});
	}
	public JTextField getTxtName(){
		return tName;
	}
	public JPasswordField getTxtPassword(){
		return tPass;
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
		LoginView login = new LoginView();
		login.setVisible(true);
	}
}