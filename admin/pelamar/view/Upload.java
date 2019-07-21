package projectsim.admin.pelamar.view;

import javax.swing.filechooser.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.io.*;

public class Upload extends JFrame{
	private JPanel topPanel = new JPanel();
	private JPanel bottomPanel = new JPanel();
	//private JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getCurrentDirectory());
	private JFileChooser chooser = new JFileChooser();
	private File file;
	private JButton browse = new JButton("  Browse  ");
	private JButton select = new JButton("Select");
	private JButton cancel = new JButton("Cancel");
	private JTextField tPath = new JTextField(15);
	private PelamarView pelamarView;
	private String pathReal;
	private InputStream image;
	public Upload(PelamarView pelamarView){
		
		super("Upload");
		this.pelamarView = pelamarView;
		setResizable(false);
		setSize(400, 550);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		topPanel.setLayout(new BorderLayout());
		topPanel.setBorder(BorderFactory.createEtchedBorder());
		
		bottomPanel.setLayout(new GridLayout(2,1,5,5));
		
		bottomPanel.add(top());
		bottomPanel.add(bottom());
		
		
		add("North", new JPanel());
		add("West", new JPanel());
		add("East", new JPanel());
		add("Center", topPanel);
		add("South", bottomPanel);
		
		browse.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				chooser.setDialogTitle("Choose an image");
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "png", "JPEG", "jpg");
				File workingDirectory = new File(System.getProperty("user.dir"));
				chooser.setCurrentDirectory(workingDirectory);
				chooser.addChoosableFileFilter(filter);
				int returnValue = chooser.showSaveDialog(null);
				if(returnValue == JFileChooser.APPROVE_OPTION){
					if(chooser.getSelectedFile().isFile()){
						file = chooser.getSelectedFile();
						tPath.setText(chooser.getSelectedFile().getPath());
						String path = chooser.getSelectedFile().getAbsolutePath();
						pathReal = path.replace("\\", "\\\\");
						
						ImageIcon image = new ImageIcon(pathReal);
						Image i = image.getImage();
						Image newimg = i.getScaledInstance(350,415, java.awt.Image.SCALE_SMOOTH);
						image = new ImageIcon(newimg);
						
						JLabel label = new JLabel(image);
						
						topPanel.removeAll();
						topPanel.add("Center",label);
						topPanel.validate();
						
					}
				}
			}
		});
		select.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				pelamarView.getTxtFoto().setText(pathReal);
				setVisible(false);
			}
		});
		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				setVisible(false);
			}
		});
		
	}
	public Upload(PelamarView pelamarView, InputStream image){
		
		super("Upload");
		this.pelamarView = pelamarView;
		this.image = image;
		setResizable(false);
		setSize(400, 550);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		topPanel.setLayout(new BorderLayout());
		topPanel.setBorder(BorderFactory.createEtchedBorder());
		
		bottomPanel.setLayout(new GridLayout(2,1,5,5));
		
		bottomPanel.add(top());
		bottomPanel.add(bottom());
		
		
		add("North", new JPanel());
		add("West", new JPanel());
		add("East", new JPanel());
		add("Center", topPanel);
		add("South", bottomPanel);
		
		browse.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				chooser.setDialogTitle("Choose an image");
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "png", "JPEG", "jpg");
				File workingDirectory = new File(System.getProperty("user.dir"));
				chooser.setCurrentDirectory(workingDirectory);
				chooser.addChoosableFileFilter(filter);
				int returnValue = chooser.showSaveDialog(null);
				if(returnValue == JFileChooser.APPROVE_OPTION){
					if(chooser.getSelectedFile().isFile()){
						file = chooser.getSelectedFile();
						tPath.setText(chooser.getSelectedFile().getPath());
						String path = chooser.getSelectedFile().getAbsolutePath();
						pathReal = path.replace("\\", "\\\\");
						
						ImageIcon image = new ImageIcon(pathReal);
						Image i = image.getImage();
						Image newimg = i.getScaledInstance(350,415, java.awt.Image.SCALE_SMOOTH);
						image = new ImageIcon(newimg);
						
						JLabel label = new JLabel(image);
						
						topPanel.removeAll();
						topPanel.add("Center",label);
						topPanel.validate();
						
					}
				}
			}
		});
		select.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				pelamarView.getTxtFoto().setText(pathReal);
				setVisible(false);
			}
		});
		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				setVisible(false);
			}
		});
		try{
			BufferedImage img = ImageIO.read(image);
			ImageIcon icon = new ImageIcon(img);
			JLabel label = new JLabel(icon);
			topPanel.removeAll();
			topPanel.add("Center",label);
			topPanel.validate();
		}catch(IOException e){
			
		}
						
	}
	public JPanel top(){
		JPanel p = new JPanel();
		p.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		p.add(tPath);
		p.add(browse);
		return p;
	}
	public JPanel bottom(){
		JPanel p = new JPanel();
		p.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		p.add(select);
		p.add(cancel);
		
		return p;
	}
}