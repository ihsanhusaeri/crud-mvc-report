package projectsim.kadis.grafik.service;

import projectsim.connection.*;
import projectsim.error.*;
import projectsim.kadis.grafik.model.*;
import java.util.*;
import java.sql.*;
public class GrafikDao{
	private String lowonganQuery = "Select * from perusahaan";
	private String pelamarQuery = "Select * from pelamar";
	private String karyawanQuery = "Select * from perusahaan";
	private PreparedStatement lowonganStat;
	private PreparedStatement pelamarStat;
	private PreparedStatement karyawanStat;
	private Connection connection;
	private String tahun;
	private long years[];
	private long totals[];
	private long startYear = 2012;
	private int jumlahKuota2012 = 0;
	private int jumlahKuota2013 = 0;
	private int jumlahKuota2014 = 0;
	private int jumlahKuota2015 = 0;
	private int jumlahKuota2016 = 0;
	private int jumlahKuota2017 = 0;
	private int jumlahLaki2012 = 0;
	private int jumlahPerempuan2012 = 0;
	private int jumlahLaki2013 = 0;
	private int jumlahPerempuan2013 = 0;
	private int jumlahLaki2014 = 0;
	private int jumlahPerempuan2014 = 0;
	private int jumlahLaki2015 = 0;
	private int jumlahPerempuan2015 = 0;
	private int jumlahLaki2016 = 0;
	private int jumlahPerempuan2016 = 0;
	private int jumlahLaki2017 = 0;
	private int jumlahPerempuan2017 = 0;
	private Map<Integer, List<Lowongan>> lowonganByYear;
	private Map<Integer, List<JumlahPelamar>> pelamarByYear;
	private Map<Long, List<Long>> karyawanByYear;
	private List<JumlahKaryawan> listKaryawan;
	private List<String> listTahun;
	private List<Long> listJumlah;
	public GrafikDao(){
		try{
			connection = ConnectionDatabase.getConnection();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public Map<Integer, List<Lowongan>> getLowongan()throws DisnakerException{
		lowonganStat = null;
		try{
			connection.setAutoCommit(false);
			lowonganStat = connection.prepareStatement(lowonganQuery);
			ResultSet set = lowonganStat.executeQuery();
			while(set.next()){
				tahun = set.getString("tahun");
				switch(tahun){
					case "2012":
						jumlahKuota2012 += set.getInt("kuota");
						break;
					case "2013":
						jumlahKuota2013 += set.getInt("kuota");
						break;
					case "2014":
						jumlahKuota2014 += set.getInt("kuota");
						break;
					case "2015":
						jumlahKuota2015 += set.getInt("kuota");
						break;
					case "2016":
						jumlahKuota2016 += set.getInt("kuota");
						break;
					case "2017":
						jumlahKuota2017 += set.getInt("kuota");
						break;
					default:
						break;
					
				}
			}
			lowonganByYear = new TreeMap<Integer, List<Lowongan>>();
			
			List<Lowongan> lowongan2012 = new ArrayList<Lowongan>();
			Lowongan a = new Lowongan();
			a.setJumlahLowongan(jumlahKuota2012);
			a.setTahun(2012);
			lowongan2012.add(a);
			lowonganByYear.put(2012, lowongan2012);
			
			List<Lowongan> lowongan2013 = new ArrayList<Lowongan>();
			Lowongan b = new Lowongan();
			b.setJumlahLowongan(jumlahKuota2013);
			b.setTahun(2013);
			lowongan2013.add(b);
			lowonganByYear.put(2013, lowongan2013);
			
			List<Lowongan> lowongan2014 = new ArrayList<Lowongan>();
			Lowongan c = new Lowongan();
			c.setJumlahLowongan(jumlahKuota2014);
			c.setTahun(2014);
			lowongan2014.add(c);
			lowonganByYear.put(2014, lowongan2014);
			
			List<Lowongan> lowongan2015 = new ArrayList<Lowongan>();
			Lowongan d = new Lowongan();
			d.setJumlahLowongan(jumlahKuota2015);
			d.setTahun(2015);
			lowongan2015.add(d);
			lowonganByYear.put(2015, lowongan2015);
			
			List<Lowongan> lowongan2016 = new ArrayList<Lowongan>();
			Lowongan e = new Lowongan();
			e.setJumlahLowongan(jumlahKuota2016);
			e.setTahun(2016);
			lowongan2016.add(e);
			lowonganByYear.put(2016, lowongan2016);
			
			List<Lowongan> lowongan2017 = new ArrayList<Lowongan>();
			Lowongan f = new Lowongan();
			f.setJumlahLowongan(jumlahKuota2017);
			f.setTahun(2017);
			lowongan2017.add(f);
			lowonganByYear.put(2017, lowongan2017);
			
			jumlahKuota2012=0;
			jumlahKuota2013=0;
			jumlahKuota2014=0;
			jumlahKuota2015=0;
			jumlahKuota2016=0;
			jumlahKuota2017=0;
			
			return lowonganByYear;
		}catch(SQLException e){
			try{
				connection.rollback();
			}catch(SQLException sqle){
				
			}
			throw new DisnakerException(e.getMessage());
		}finally{
			try{
				connection.setAutoCommit(true);
			}catch(SQLException exx){
				
			}
			if(lowonganStat != null){
				try{
					lowonganStat.close();
				}catch(SQLException ex){
					
				}
			}
		}
	}
	public Map<Long, List<Long>> getKaryawan()throws DisnakerException{
		karyawanStat = null;
		listKaryawan = new ArrayList<JumlahKaryawan>();
		listTahun = new ArrayList<String>();
		
		karyawanByYear = new TreeMap<Long, List<Long>>();
		try{
			connection.setAutoCommit(false);
			karyawanStat = connection.prepareStatement(karyawanQuery);
			ResultSet set = karyawanStat.executeQuery();
			while(set.next()){
				JumlahKaryawan jumlah = new JumlahKaryawan();
				jumlah.setTahun(set.getString("tahun"));
				jumlah.setJumlahKaryawan(set.getInt("j_karyawan"));
				
				listTahun.add(set.getString("tahun"));
				
				listKaryawan.add(jumlah);
			}
			long n = listTahun.stream().distinct().count();
			years = new long[(int)n];
			totals = new long[(int)n];
			for(long i=0; i < totals.length; i++){
				totals[(int)i] = 0l;
			}
			for(long i = 0; i < n; i++){
				years[(int)i] = startYear + i;
			}
			
			for(long i=0; i < years.length;i++){
				listJumlah = new ArrayList<Long>();
				for(long j =0; j < listKaryawan.size(); j++){
					if(years[(int)i] == Long.parseLong(listKaryawan.get((int)j).getTahun())){
						totals[(int)i] += listKaryawan.get((int)j).getJumlahKaryawan();
					}
				}
				System.out.println((startYear+i)+" Total ke "+i+ " : "+totals[(int)i]);
				listJumlah.add(totals[(int)i]);
				karyawanByYear.put(startYear+i, listJumlah);
			}
			System.out.println("n : "+n);
			
			return karyawanByYear;
		}catch(SQLException e){
			try{
				connection.rollback();
			}catch(SQLException sqle){
				
			}
			throw new DisnakerException(e.getMessage());
		}finally{
			try{
				connection.setAutoCommit(true);
			}catch(SQLException exx){
				
			}
			if(karyawanStat != null){
				try{
					karyawanStat.close();
				}catch(SQLException ex){
					
				}
			}
		}
	}
	public Map<Integer, List<JumlahPelamar>> getJumlahPelamar()throws DisnakerException{
		pelamarStat = null;
		try{ 
			connection.setAutoCommit(false);
			pelamarStat = connection.prepareStatement(pelamarQuery);
			ResultSet set = pelamarStat.executeQuery();
			while(set.next()){
				tahun = set.getString("tahun");
				switch(tahun){
					case "2012":
						if(set.getString("jenis_K").equals("Laki-Laki")){
							jumlahLaki2012 += 1;
						}else{
							jumlahPerempuan2012 += 1;
						}
						break;
					case "2013":
						if(set.getString("jenis_K").equals("Laki-Laki")){
							jumlahLaki2013 += 1;
						}else{
							jumlahPerempuan2013 += 1;
						}
						break;
					case "2014":
						if(set.getString("jenis_K").equals("Laki-Laki")){
							jumlahLaki2014 += 1;
						}else{
							jumlahPerempuan2014 += 1;
						}
						break;
					case "2015":
						if(set.getString("jenis_K").equals("Laki-Laki")){
							jumlahLaki2015 += 1;
						}else{
							jumlahPerempuan2015 += 1;
						}
						break;
					case "2016":
						if(set.getString("jenis_K").equals("Laki-Laki")){
							jumlahLaki2016 += 1;
						}else{
							jumlahPerempuan2016 += 1;
						}
						break;
					case "2017":
						if(set.getString("jenis_K").equals("Laki-Laki")){
							jumlahLaki2017 += 1;
						}else{
							jumlahPerempuan2017 += 1;
						}
						break;
					default:
						break;
					
				}
			}
			pelamarByYear = new TreeMap<Integer, List<JumlahPelamar>>();
			
			List<JumlahPelamar> jumlahPelamar2012 = new ArrayList<JumlahPelamar>();
			JumlahPelamar a = new JumlahPelamar();
			a.setJumlahLaki(jumlahLaki2012);
			a.setJumlahPerempuan(jumlahPerempuan2012);
			jumlahPelamar2012.add(a);
			pelamarByYear.put(2012, jumlahPelamar2012);
			
			List<JumlahPelamar> jumlahPelamar2013 = new ArrayList<JumlahPelamar>();
			JumlahPelamar b = new JumlahPelamar();
			b.setJumlahLaki(jumlahLaki2013);
			b.setJumlahPerempuan(jumlahPerempuan2013);
			jumlahPelamar2013.add(b);
			pelamarByYear.put(2013, jumlahPelamar2013);
			
			List<JumlahPelamar> jumlahPelamar2014 = new ArrayList<JumlahPelamar>();
			JumlahPelamar c = new JumlahPelamar();
			c.setJumlahLaki(jumlahLaki2014);
			c.setJumlahPerempuan(jumlahPerempuan2014);
			jumlahPelamar2014.add(c);
			pelamarByYear.put(2014, jumlahPelamar2014);
			
			List<JumlahPelamar> jumlahPelamar2015 = new ArrayList<JumlahPelamar>();
			JumlahPelamar d = new JumlahPelamar();
			d.setJumlahLaki(jumlahLaki2015);
			d.setJumlahPerempuan(jumlahPerempuan2015);
			jumlahPelamar2015.add(d);
			pelamarByYear.put(2015, jumlahPelamar2015);
			
			List<JumlahPelamar> jumlahPelamar2016 = new ArrayList<JumlahPelamar>();
			JumlahPelamar e = new JumlahPelamar();
			e.setJumlahLaki(jumlahLaki2016);
			e.setJumlahPerempuan(jumlahPerempuan2016);
			jumlahPelamar2016.add(e);
			pelamarByYear.put(2016, jumlahPelamar2016);
			
			List<JumlahPelamar> jumlahPelamar2017 = new ArrayList<JumlahPelamar>();
			JumlahPelamar f = new JumlahPelamar();
			f.setJumlahLaki(jumlahLaki2017);
			f.setJumlahPerempuan(jumlahPerempuan2017);
			jumlahPelamar2017.add(f);
			pelamarByYear.put(2017, jumlahPelamar2017);
			
			jumlahLaki2012=0;
			jumlahLaki2013=0;
			jumlahLaki2014=0;
			jumlahLaki2015=0;
			jumlahLaki2016=0;
			jumlahLaki2017=0;
			jumlahPerempuan2012=0;
			jumlahPerempuan2013=0;
			jumlahPerempuan2014=0;
			jumlahPerempuan2015=0;
			jumlahPerempuan2016=0;
			jumlahPerempuan2017=0;
			
			return pelamarByYear;
		}catch(SQLException e){
			try{
				connection.rollback();
			}catch(SQLException sqle){
				
			}
			throw new DisnakerException(e.getMessage());
		}finally{
			try{
				connection.setAutoCommit(true);
			}catch(SQLException exx){
				
			}
			if(lowonganStat != null){
				try{
					lowonganStat.close();
				}catch(SQLException ex){
					
				}
			}
		}
	}
	
}