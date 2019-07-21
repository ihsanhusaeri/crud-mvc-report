package projectsim.kadis.grafik.model;

import java.lang.Comparable;
public class Lowongan{
	private long tahun;
	private long jumLowongan;
	
	public void setTahun(long tahun){
		this.tahun = tahun;
	}
	public long getTahun(){
		return tahun;
	}
	public void setJumlahLowongan(long jumLowongan){
		this.jumLowongan = jumLowongan;
	}
	public long getJumlahLowongan(){
		return jumLowongan;
	}
	
}