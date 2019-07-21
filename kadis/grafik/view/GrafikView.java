package projectsim.kadis.grafik.view;

import java.awt.Color;
import java.awt.*;
import projectsim.kadis.grafik.controller.*;
import java.text.*;
import java.text.DecimalFormat;
import java.awt.event.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.category.*;
import javax.swing.*;
import java.util.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.sql.*;
import java.io.*;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;
import org.jfree.chart.renderer.category.*;
import org.jfree.chart.renderer.*;
import com.orsoncharts.label.*;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.ui.RectangleEdge;
import static com.orsonpdf.util.TextAnchor.TOP_LEFT;
import org.jfree.ui.TextAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import static org.jfree.chart.labels.ItemLabelAnchor.CENTER;
import org.jfree.chart.labels.ItemLabelAnchor;


public class GrafikView extends JPanel{
	private JPanel topPanel = new JPanel();
	private JPanel rightPanel = new JPanel();
	private JPanel leftPanel = new JPanel();
	private JPanel bottomPanel = new JPanel();
	private JPanel centerPanel = new JPanel();
	private JButton bShow = new JButton("    Show    ");
	private String[] kategori = {"Jumlah Pelamar", "Jumlah Lowongan", "Jumlah Karyawan"};
	private JComboBox comboKategori = new JComboBox(kategori);
	private DefaultCategoryDataset dodLowongan;
	private DefaultCategoryDataset dodPelamar;
	private DefaultCategoryDataset dodKaryawan;
	private GrafikController grafikController = new GrafikController(this);
	public GrafikView(){
		setLayout(new BorderLayout());
		
		bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		centerPanel.setLayout(new BorderLayout());
		
		topPanel.setBackground(Color.WHITE);
		leftPanel.setBackground(Color.WHITE);
		rightPanel.setBackground(Color.WHITE);
		centerPanel.setBackground(Color.WHITE);
		bottomPanel.setBackground(Color.decode("#c9fccf"));		
		
		bottomPanel.add(new JLabel("Grafik  : "));
		bottomPanel.add(comboKategori);
		bottomPanel.add(bShow);
		
		centerPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#c9fccf")));
		
		bShow.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				if(comboKategori.getSelectedItem().equals("Jumlah Lowongan")){
					dodLowongan= new DefaultCategoryDataset();
					JFreeChart jChart = ChartFactory.createBarChart3D("Jumlah Lowongan Kerja Pertahun", "", "", dodLowongan, PlotOrientation.VERTICAL, false, false, false);
					final CategoryPlot plot = jChart.getCategoryPlot();
					plot.setRangeGridlinePaint(Color.BLACK);
					jChart.setBackgroundPaint(Color.decode("#FFFFFF"));
				
					BarRenderer renderer = (BarRenderer) plot.getRenderer();
					DecimalFormat format = new DecimalFormat("##.#");
					renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}", format));
					plot.setRenderer(renderer);
					renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.TOP_LEFT));
					renderer.setItemLabelsVisible(true);
					jChart.getCategoryPlot().setRenderer(renderer);
					/*LegendTitle legend = new LegendTitle(jChart.getPlot());
					//legend.setItemFont(LEGEND_FONT);
					legend.setBorder(0,0,0,0);
					legend.setPosition(RectangleEdge.BOTTOM);
					jChart.addLegend(legend);		
					*/
					grafikController.setValueJumlahLowongan();
					ChartPanel frame = new ChartPanel(jChart);
					centerPanel.removeAll();
					centerPanel.add(frame, BorderLayout.CENTER);
					centerPanel.validate();	
				}else if(comboKategori.getSelectedItem().equals("Jumlah Pelamar")){
					dodPelamar= new DefaultCategoryDataset();
					JFreeChart jChart = ChartFactory.createBarChart("Jumlah Pelamar Per Tahun", "", "", dodPelamar, PlotOrientation.VERTICAL, false, false, false);
					final CategoryPlot plot = jChart.getCategoryPlot();
					plot.setRangeGridlinePaint(Color.BLACK);
					jChart.setBackgroundPaint(Color.decode("#FFFFFF"));
				
					BarRenderer renderer = (BarRenderer) plot.getRenderer();
					DecimalFormat format = new DecimalFormat("##.#");
					renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}", format));
					plot.setRenderer(renderer);
					renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.TOP_LEFT));
					renderer.setItemLabelsVisible(true);
					jChart.getCategoryPlot().setRenderer(renderer);
					LegendTitle legend = new LegendTitle(jChart.getPlot());
					//legend.setItemFont(LEGEND_FONT);
					legend.setBorder(0,0,0,0);
					legend.setPosition(RectangleEdge.BOTTOM);
					jChart.addLegend(legend);		
					grafikController.setValueJumlahPelamar();
				
					ChartPanel frame = new ChartPanel(jChart);
					centerPanel.removeAll();
					centerPanel.add(frame, BorderLayout.CENTER);
					centerPanel.validate();	
				}else{
					dodKaryawan= new DefaultCategoryDataset();
					JFreeChart jChart = ChartFactory.createBarChart("Jumlah Karyawan Per Tahun", "", "", dodKaryawan, PlotOrientation.VERTICAL, false, false, false);
					final CategoryPlot plot = jChart.getCategoryPlot();
					plot.setRangeGridlinePaint(Color.BLACK);
					jChart.setBackgroundPaint(Color.decode("#FFFFFF"));
				
					BarRenderer renderer = (BarRenderer) plot.getRenderer();
					DecimalFormat format = new DecimalFormat("##.#");
					renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}", format));
					plot.setRenderer(renderer);
					renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.TOP_LEFT));
					renderer.setItemLabelsVisible(true);
					jChart.getCategoryPlot().setRenderer(renderer);
					LegendTitle legend = new LegendTitle(jChart.getPlot());
					//legend.setItemFont(LEGEND_FONT);
					legend.setBorder(0,0,0,0);
					legend.setPosition(RectangleEdge.BOTTOM);
					jChart.addLegend(legend);		
					
					grafikController.setValueJumlahKaryawan();
				
					ChartPanel frame = new ChartPanel(jChart);
					centerPanel.removeAll();
					centerPanel.add(frame, BorderLayout.CENTER);
					centerPanel.validate();	
				}
			}
		});
		
		add("Center", centerPanel);
		add("South", bottomPanel);
	}
	public DefaultCategoryDataset getDatasetLowongan(){
		return dodLowongan;
	}
	public DefaultCategoryDataset getDatasetPelamar(){
		return dodPelamar;
	}
	public DefaultCategoryDataset getDatasetKaryawan(){
		return dodKaryawan;
	}
}