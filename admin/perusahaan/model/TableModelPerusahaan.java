package projectsim.admin.perusahaan.model;

import java.util.*;
import javax.swing.table.*;

public class TableModelPerusahaan extends AbstractTableModel{
	private List<Perusahaan> listPerusahaan;
	public TableModelPerusahaan(){
		
	}
	public TableModelPerusahaan(List<Perusahaan> listPerusahaan){
		this.listPerusahaan = listPerusahaan;
	}
	public int getColumnCount(){
		return 10;
	}
	public int getRowCount(){
		try{
			return listPerusahaan.size();
		}catch(Exception er){
			return 0;
		}
	}
	public String getColumnName(int column){
		switch(column){
			case 0:
				return "No";
			case 1:
				return "ID";
			case 2:
				return "K_Perusahaan";
			case 3:
				return "Nama";
			case 4:
				return "Alamat";
			case 5:
				return "Kategori";
			case 6:
				return "Tanggal Berdiri";
			case 7:
				return "J_Karyawan";
			case 8:
				return "Kuota";	
			case 9:
				return "Status";	
			default:
				return null;
		}
	}
	public Object getValueAt(int row, int column){
		switch(column){
			case 0:
				return listPerusahaan.get(row).getNo();
			case 1:
				return listPerusahaan.get(row).getId();
			case 2:
				return listPerusahaan.get(row).getKodePerusahaan();
			case 3:
				return listPerusahaan.get(row).getName();
			case 4:
				return listPerusahaan.get(row).getAlamat();
			case 5:
				return listPerusahaan.get(row).getKategori();
			case 6:
				return listPerusahaan.get(row).getTanggalBerdiri();
			case 7:
				return listPerusahaan.get(row).getJumlahKaryawan();
			case 8:
				return listPerusahaan.get(row).getKuotaKaryawan();
			case 9:
				return listPerusahaan.get(row).getStatus();
			default:
				return null;
		}
	}
}