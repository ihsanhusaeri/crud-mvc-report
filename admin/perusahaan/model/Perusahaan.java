package projectsim.admin.perusahaan.model;

public class Perusahaan{
	private int no;
	private String id;
	private String kodePerusahaan;
	private String name;
	private String kategori;
	private String alamat;
	private int jumlahKaryawan;
	private int kuotaKaryawan;
	private String tanggalDidirikan;
	private String tahun;
	private String status;
	public Perusahaan(){
		
	}
	public void setNo(int no){
		this.no = no;
	}
	public int getNo(){
		return no;
	}
	public void setId(String id){
		this.id = id;
	}
	public String getId(){
		return id;
	}
	public void setKodePerusahaan(String kodePerusahaan){
		this.kodePerusahaan = kodePerusahaan;
	}
	public String getKodePerusahaan(){
		return kodePerusahaan;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void setKategori(String kategori){
		this.kategori = kategori;
	}
	public String getKategori(){
		return kategori;
	}
	public void setJumlahKaryawan(int jumlahKaryawan){
		this.jumlahKaryawan = jumlahKaryawan;
	}
	public int getJumlahKaryawan(){
		return jumlahKaryawan;
	}
	public void setKuotaKaryawan(int kuotaKaryawan){
		this.kuotaKaryawan = kuotaKaryawan;
	}
	public int getKuotaKaryawan(){
		return kuotaKaryawan;
	}
	public void setAlamat(String alamat){
		this.alamat = alamat;
	}
	public String getAlamat(){
		return alamat;
	}
	public void setTanggalBerdiri(String tanggalDidirikan){
		this.tanggalDidirikan = tanggalDidirikan;
	}
	public String getTanggalBerdiri(){
		return tanggalDidirikan;
	}
	public void setTahun(String tahun){
		this.tahun = tahun;
	}
	public String getTahun(){
		return tahun;
	}
	public void setStatus(String status){
		this.status = status;
	}
	public String getStatus(){
		return status;
	}
}