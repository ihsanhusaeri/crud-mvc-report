package projectsim.admin.pelamar.model;

import java.io.*;

public class Pelamar{
	private int id;
	private String name;
	private String jenisK;
	private String ttl;
	private String pendidikan;
	private String alamat;
	private String noKtp;
	private InputStream suratKesehatan;
	private String tahun;
	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return id;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void setJenisK(String jenisK){
		this.jenisK = jenisK;
	}
	public String getJenisK(){
		return jenisK;
	}
	public void setTTL(String ttl){
		this.ttl = ttl;
		
	}
	public String getTTL(){
		return ttl;
	}
	public void setPendidikan(String pendidikan){
		this.pendidikan = pendidikan;
	}
	public String getPendidikan(){
		return pendidikan;
	}
	public void setAlamat(String alamat){
		this.alamat = alamat;
	}
	public String getAlamat(){
		return alamat;
	}
	public void setNoKtp(String noKtp){
		this.noKtp = noKtp;
	}
	public String getNoKtp(){
		return noKtp;
	}
	public void setSuratKesehatan(InputStream suratKesehatan){
		this.suratKesehatan = suratKesehatan;
	}
	public InputStream getSuratKesehatan(){
		return suratKesehatan;
	}
	public void setTahun(String tahun){
		this.tahun= tahun;
	}
	public String getTahun(){
		return tahun;
	}
}