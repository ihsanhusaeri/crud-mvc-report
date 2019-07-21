package projectsim.kadis.controller;

import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
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
import projectsim.kadis.laporan.view.*;
import java.sql.*;

public class LaporanController{
	private LaporanView laporanView;
	public LaporanController(LaporanView laporanView){
		this.laporanView = laporanView;
	}
	public void showLaporanPerusahaan(){
		Connection con;
		String url = "jdbc:mysql://localhost/disnaker";
		String user = "root";
		String password ="";
		try{
			Class.forName("com.mysql.jdbc.Driver");	
			Connection conn = (Connection) DriverManager.getConnection(url,user,password);
			InputStream is = new FileInputStream(new File("D:\\projectsim\\report\\Perusahaan.jrxml"));
			JasperDesign jd = JRXmlLoader.load(is);
			JasperReport jr = JasperCompileManager.compileReport(jd);
			Map<String, String> map=new HashMap<String, String>();
			map.put("jasper reports", "employee report");		
			JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
			JRViewer jrv = new JRViewer(jp);
			laporanView.getCenterPanel.setLayout(new BorderLayout());
			laporanView.getCenterPanel.repaint();
			laporanView.getCenterPanel.add(jrv);
			laporanView.getCenterPanel.revalidate();
			//JasperViewer.viewReport(jp, false);
		}catch(Exception ex){
					
		}
	}
}