package projectsim.kadis.laporan.view;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;
import org.jfree.chart.renderer.category.*;
import com.orsoncharts.label.*;
import org.jfree.chart.title.LegendTitle;
import org.jfree.ui.RectangleEdge;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.io.*;
import projectsim.kadis.laporan.controller.*;

public class LaporanView extends JPanel{
	private JButton bShow = new JButton("   Show   ");
	private String []laporan = {"Perusahaan    ", "Pegawai", "Pelamar"};
	private JComboBox comboLaporan = new JComboBox(laporan);
	private JPanel bottomPanel = new JPanel();
	private JPanel centerPanel= new JPanel();
	private JPanel leftPanel= new JPanel();
	private JPanel rightPanel= new JPanel();
	private JPanel topPanel= new JPanel();
	private LaporanController laporanController = new LaporanController(this);
	public LaporanView(){
		setLayout(new BorderLayout());
		topPanel.setBackground(Color.WHITE);
		leftPanel.setBackground(Color.WHITE);
		rightPanel.setBackground(Color.WHITE);
		centerPanel.setBackground(Color.WHITE);
		bottomPanel.setBackground(Color.decode("#c9fccf"));
		
		centerPanel.setLayout(new BorderLayout());
		
		bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10,10));
		bottomPanel.add(new JLabel("Laporan : "));
		bottomPanel.add(comboLaporan);
		bottomPanel.add(bShow);
		
		centerPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#c9fccf")));
		
		add("South",bottomPanel);
		add("Center",centerPanel);
		add("East",rightPanel);
		add("West",leftPanel);
		add("North",topPanel);
		
		bShow.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String laporanSelected = comboLaporan.getSelectedItem().toString();
				if(laporanSelected.equals("Perusahaan")){
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR))
					laporanController.showLaporanPerusahaan();
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			}
		});
	}
	public JPanel getCenterPanel(){
		return centerPanel;
	}
}