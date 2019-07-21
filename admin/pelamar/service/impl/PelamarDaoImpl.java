package projectsim.admin.pelamar.service.impl;

import projectsim.admin.pelamar.service.*;
import projectsim.admin.pelamar.model.*;
import projectsim.error.*;
import projectsim.connection.*;
import java.sql.*;
import java.io.*;
import java.util.*;

public class PelamarDaoImpl implements PelamarDao{
	private Connection connection;
	private String insertQuery = "Insert into pelamar(nama,tgl_lahir,jenis_k,pendidikan, alamat,no_ktp,surat_kesehatan,tahun) values(?,?,?,?,?,?,?,?)";
	private String updateQuery = "Update pelamar set nama=?, tgl_lahir=?,jenis_k=?,pendidikan=?,alamat=?,no_ktp=? where id=?";
	private String deleteQuery = "Delete from pelamar where id=?";
	private String getAllDataQuery = "Select * from pelamar";
	private final String getMaxId = "Select max(id) From pelamar";
	private String getPelamarQuery;
	private PreparedStatement insertStat;
	private PreparedStatement updateStat;
	private PreparedStatement deleteStat;
	private PreparedStatement getPelamarStat;
	private PreparedStatement getAllDataStat;
	private Pelamar pelamar;
	private int id;
	private List<Pelamar> listPelamar;
	public PelamarDaoImpl(){
		try{
			connection = ConnectionDatabase.getConnection();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void save(Pelamar pelamar)throws DisnakerException{
		insertStat = null;
		try{
			connection.setAutoCommit(false);
			insertStat = connection.prepareStatement(insertQuery);
			insertStat.setString(1, pelamar.getName());
			insertStat.setString(2, pelamar.getTTL());
			insertStat.setString(3, pelamar.getJenisK());
			insertStat.setString(4, pelamar.getPendidikan());
			insertStat.setString(5, pelamar.getAlamat());
			insertStat.setString(6, pelamar.getNoKtp());
			insertStat.setBlob(7, pelamar.getSuratKesehatan());
			insertStat.setString(8, pelamar.getTahun());
			System.out.println(pelamar.getTahun());
			
			insertStat.executeUpdate();
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
			if(insertStat != null){
				try{
					insertStat.close();
				}catch(SQLException ex){
					
				}
			}
		}
	}
	public void edit(Pelamar pelamar) throws DisnakerException{
		updateStat=null;
		try{
			connection.setAutoCommit(false);
			updateStat = connection.prepareStatement(updateQuery);
			updateStat.setString(1, pelamar.getName());
			updateStat.setString(2, pelamar.getTTL());
			updateStat.setString(3, pelamar.getJenisK());
			updateStat.setString(4, pelamar.getPendidikan());
			updateStat.setString(5, pelamar.getAlamat());
			updateStat.setString(6, pelamar.getNoKtp());
			//updateStat.setBlob(7, pelamar.getSuratKesehatan());
			updateStat.setInt(7, pelamar.getId());
			updateStat.executeUpdate();
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
			if(updateStat != null){
				try{
					updateStat.close();
				}catch(SQLException ex){
					
				}
			}
		}
	}
	public void delete(int id)throws DisnakerException{
		deleteStat = null;
		try{
			connection.setAutoCommit(false);
			deleteStat = connection.prepareStatement(deleteQuery);
			deleteStat.setInt(1, id);
			deleteStat.executeUpdate();
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
			if(deleteStat != null){
				try{
					deleteStat.close();
				}catch(SQLException ex){
					
				}
			}
		}
	}
	public List<Pelamar> getPelamar(String search, String kategori)throws DisnakerException{
		getPelamarStat = null;
		getPelamarQuery= "Select * from pelamar where "+kategori+"=?";
		try{
			connection.setAutoCommit(false);
			listPelamar = new ArrayList<Pelamar>();
			getPelamarStat = connection.prepareStatement(getPelamarQuery);
			if(kategori.equals("id")){
				getPelamarStat.setInt(1, Integer.parseInt(search));
			}else{
				getPelamarStat.setString(1, search);
			}
			ResultSet set = getPelamarStat.executeQuery();
			int i = 0;
			//if(set.next()){
			while(set.next()){
				pelamar= new Pelamar();
				pelamar.setId(set.getInt(1));
				pelamar.setName(set.getString(2));
				pelamar.setTTL(set.getString(3));
				pelamar.setJenisK(set.getString(4));
				pelamar.setPendidikan(set.getString(5));
				pelamar.setAlamat(set.getString(6));
				pelamar.setNoKtp(set.getString(7));
				//Blob image =set.getBlob(8);
				pelamar.setSuratKesehatan(set.getBinaryStream(8));
				
				listPelamar.add(pelamar);
				i++;
			}
			if(i == 0){
				throw new DisnakerException("Data dengan "+kategori+" \""+search+"\" tidak ditemukan!");
			}
			connection.commit();
			
			return listPelamar;
		}catch(SQLException e){
			try{
				connection.rollback();
			}catch(SQLException sqle){
				
			}
			e.printStackTrace();
			throw new DisnakerException(e.getMessage());
			
		}finally{
			try{
				connection.setAutoCommit(true);
			}catch(SQLException exx){
				exx.printStackTrace();
			}
			if(getPelamarStat != null){
				try{
					getPelamarStat.close();
				}catch(SQLException ex){
					ex.printStackTrace();
				}
			}
		}
	}
	public List<Pelamar> getAllData() throws DisnakerException{
		getAllDataStat = null;
		try{
			listPelamar = new ArrayList<Pelamar>();
			connection.setAutoCommit(false);
			getAllDataStat = connection.prepareStatement(getAllDataQuery);
			ResultSet set = getAllDataStat.executeQuery();
			int i = 1;
			while(set.next()){
				pelamar = new Pelamar();
				pelamar.setId(set.getInt(1));
				pelamar.setName(set.getString(2));
				pelamar.setTTL(set.getString(3));
				pelamar.setJenisK(set.getString(4));
				pelamar.setPendidikan(set.getString(5));
				pelamar.setAlamat(set.getString(6));
				pelamar.setNoKtp(set.getString(7));
				//Blob image =set.getBlob(8);
				pelamar.setSuratKesehatan(set.getBinaryStream(8));
				
				listPelamar.add(pelamar);
				i++;
			}
			connection.commit();
			return listPelamar;
		}catch(SQLException e){
			try{
				connection.rollback();
			}catch(SQLException sqle){
				
			}
			System.out.println("gagal ngambil data");
			throw new DisnakerException(e.getMessage());
			
		}finally{
			try{
				connection.setAutoCommit(true);
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
			
			connection.setAutoCommit(false);
			stat = connection.prepareStatement(getMaxId);
			ResultSet set = stat.executeQuery();
			if(set.next()){
				try{
					id = set.getInt(1)+1;
					
				}catch(Exception err){
					return 10001;
				}
			}
			connection.commit();
			return id;
		}catch(SQLException er){
			try{
				connection.rollback();
			}catch(SQLException e){}
			throw new DisnakerException(er.getMessage());
		}finally{
			try{
				connection.setAutoCommit(true);
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
