package projectsim.admin.perusahaan.service.impl;

import projectsim.admin.perusahaan.service.*;
import projectsim.admin.perusahaan.model.*;
import projectsim.connection.*;
import projectsim.error.*;
import java.sql.*;
import java.util.*;
import java.io.*;

public class PerusahaanDaoImpl implements PerusahaanDao{
	private String insertQuery = "Insert into perusahaan(kode_perusahaan,nama,alamat,kategori,tgl_berdiri,j_karyawan,kuota,tahun,status) values(?,?,?,?,?,?,?,?,?)";
	private String updateQuery = "Update perusahaan set kode_perusahaan=?,nama=?,alamat=?,kategori=?,tgl_berdiri=?,j_karyawan=?,kuota=?,status=? where id=?";
	private String getAllDataQuery = "Select * From perusahaan";
	private String getPerusahaanQuery;
	private final String getMaxId = "Select max(id) From perusahaan";
	private String deleteQuery = "Delete from perusahaan where id=?";
	private Connection conn;
	private List<Perusahaan> perusahaans;
	private PreparedStatement insertStat;
	private PreparedStatement updateStat;
	private PreparedStatement deleteStat;
	private PreparedStatement getAllDataStat;
	private PreparedStatement getPerusahaanStat;
	private int id;
	public PerusahaanDaoImpl(){
		try{
			conn = ConnectionDatabase.getConnection();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void save(Perusahaan perusahaan) throws DisnakerException{
		insertStat = null;
		try{
			conn.setAutoCommit(false);
			insertStat = conn.prepareStatement(insertQuery);
			insertStat.setString(1,perusahaan.getKodePerusahaan());
			insertStat.setString(2, perusahaan.getName());
			insertStat.setString(3, perusahaan.getAlamat());
			insertStat.setString(4, perusahaan.getKategori());
			insertStat.setString(5, perusahaan.getTanggalBerdiri());
			insertStat.setInt(6, perusahaan.getJumlahKaryawan());
			insertStat.setInt(7, perusahaan.getKuotaKaryawan());
			insertStat.setString(8, perusahaan.getTahun());
			insertStat.setString(9, perusahaan.getStatus());
			
			insertStat.executeUpdate();
			
		}catch(SQLException e){
			try{
				conn.rollback();
			}catch(SQLException sqle){
				
			}
			throw new DisnakerException(e.getMessage());
		}finally{
			try{
				conn.setAutoCommit(true);
			}catch(SQLException exx){
				
			}
			if(insertStat != null){
				try{
					insertStat.close();
				}catch(SQLException ex){
					
				}
			}
		}
	}
	public void edit(Perusahaan perusahaan) throws DisnakerException{
		updateStat=null;
		try{
			conn.setAutoCommit(false);
			updateStat = conn.prepareStatement(updateQuery);
			updateStat.setString(1,perusahaan.getKodePerusahaan());
			updateStat.setString(2, perusahaan.getName());
			updateStat.setString(3, perusahaan.getAlamat());
			updateStat.setString(4, perusahaan.getKategori());
			updateStat.setString(5, perusahaan.getTanggalBerdiri());
			updateStat.setInt(6, perusahaan.getJumlahKaryawan());
			updateStat.setInt(7, perusahaan.getKuotaKaryawan());
			updateStat.setString(8, perusahaan.getStatus());
			updateStat.setString(9,perusahaan.getId());
			updateStat.executeUpdate();
		}catch(SQLException e){
			try{
				conn.rollback();
			}catch(SQLException sqle){
				
			}
			throw new DisnakerException(e.getMessage());
		}finally{
			try{
				conn.setAutoCommit(true);
			}catch(SQLException exx){
				
			}
			if(updateStat != null){
				try{
					updateStat.close();
				}catch(SQLException ex){
					
				}
			}
		}
	}
	public void delete(String id)throws DisnakerException{
		deleteStat = null;
		try{
			conn.setAutoCommit(false);
			deleteStat = conn.prepareStatement(deleteQuery);
			deleteStat.setString(1, id);
			deleteStat.executeUpdate();
		}catch(SQLException e){
			try{
				conn.rollback();
			}catch(SQLException sqle){
				
			}
			throw new DisnakerException(e.getMessage());
		}finally{
			try{
				conn.setAutoCommit(true);
			}catch(SQLException exx){
				
			}
			if(deleteStat != null){
				try{
					deleteStat.close();
				}catch(SQLException ex){
					
				}
			}
		}
	}
	public List<Perusahaan> getPerusahaan(String search, String kategori)throws DisnakerException{
		getPerusahaanStat = null;
		getPerusahaanQuery= "Select * from perusahaan where "+kategori+"=?";
		try{
			conn.setAutoCommit(false);
			perusahaans = new ArrayList<Perusahaan>();
			getPerusahaanStat = conn.prepareStatement(getPerusahaanQuery);
			getPerusahaanStat.setString(1, search);
			ResultSet set = getPerusahaanStat.executeQuery();
			int i = 0;
			//if(set.next()){
			while(set.next()){
				i++;
				Perusahaan p = new Perusahaan();
				p.setNo(i);
				p.setId(set.getString("id"));
				p.setKodePerusahaan(set.getString("kode_perusahaan"));
				p.setName(set.getString("nama"));
				p.setAlamat(set.getString("alamat"));
				p.setKategori(set.getString("kategori"));
				p.setTanggalBerdiri(set.getString("tgl_berdiri"));
				p.setJumlahKaryawan(set.getInt("j_karyawan"));
				p.setKuotaKaryawan(set.getInt("kuota"));
				p.setTahun(set.getString("tahun"));
				p.setStatus(set.getString("status"));
				
				perusahaans.add(p);
				
			}
			if(i == 0){
				throw new DisnakerException("Data dengan "+kategori+" \""+search+"\" tidak ditemukan!");
			}
			conn.commit();
			
			return perusahaans;
		}catch(SQLException e){
			try{
				conn.rollback();
			}catch(SQLException sqle){
				
			}
			System.out.println("gagal ngambil data");
			throw new DisnakerException(e.getMessage());
			
		}finally{
			try{
				conn.setAutoCommit(true);
			}catch(SQLException exx){
				exx.printStackTrace();
			}
			if(getPerusahaanStat != null){
				try{
					getPerusahaanStat.close();
				}catch(SQLException ex){
					ex.printStackTrace();
				}
			}
		}
	}
	public List<Perusahaan> getAllData() throws DisnakerException{
		getAllDataStat = null;
		try{
			perusahaans = new ArrayList<Perusahaan>();
			conn.setAutoCommit(false);
			getAllDataStat = conn.prepareStatement(getAllDataQuery);
			ResultSet set = getAllDataStat.executeQuery();
			int i = 1;
			while(set.next()){
				Perusahaan p = new Perusahaan();
				p.setNo(i);
				p.setId(set.getString("id"));
				p.setKodePerusahaan(set.getString("kode_perusahaan"));
				p.setName(set.getString("nama"));
				p.setAlamat(set.getString("alamat"));
				p.setKategori(set.getString("kategori"));
				p.setTanggalBerdiri(set.getString("tgl_berdiri"));
				p.setJumlahKaryawan(set.getInt("j_karyawan"));
				p.setKuotaKaryawan(set.getInt("kuota"));
				p.setTahun(set.getString("tahun"));
				p.setStatus(set.getString("status"));
				
				perusahaans.add(p);
				i++;
			}
			conn.commit();
			return perusahaans;
		}catch(SQLException e){
			try{
				conn.rollback();
			}catch(SQLException sqle){
				
			}
			System.out.println("gagal ngambil data");
			throw new DisnakerException(e.getMessage());
			
		}finally{
			try{
				conn.setAutoCommit(true);
			}catch(SQLException exx){
				exx.printStackTrace();
			}
			if(getAllDataStat != null){
				try{
					getAllDataStat.close();
				}catch(SQLException ex){
					ex.printStackTrace();
				}
			}
		}			
	}
	public int createId()throws DisnakerException{
		PreparedStatement stat = null;
		try{				
			conn.setAutoCommit(false);
			stat = conn.prepareStatement(getMaxId);
			ResultSet set = stat.executeQuery();
			if(set.next()){
				try{
					id = set.getInt(1)+1;
					
				}catch(Exception err){
					return 120001;
				}
			}
			conn.commit();
			return id;
		}catch(SQLException er){
			try{
				conn.rollback();
			}catch(SQLException e){}
			throw new DisnakerException(er.getMessage());
		}finally{
			try{
				conn.setAutoCommit(true);
			}catch(SQLException ex){}
			if(stat != null){
				try{
					stat.close();
				}catch(SQLException sqlE){
					
				}
			}
		}
	}
}