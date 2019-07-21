package projectsim.kadis.grafik.model;

public class JumlahKaryawan{
	private String tahun;
	private int jumlahKaryawan;
	public void setTahun(String tahun){
		this.tahun = tahun;
	}
	public String getTahun(){
		return tahun;
	}
	public void setJumlahKaryawan(int jumlahKaryawan){
		this.jumlahKaryawan = jumlahKaryawan;
	}
	public int getJumlahKaryawan(){
		return jumlahKaryawan;
	}
}