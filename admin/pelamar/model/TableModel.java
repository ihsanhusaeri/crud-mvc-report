package projectsim.admin.pelamar.model;

import java.util.*;
import javax.swing.table.*;

public class TableModel extends AbstractTableModel{
	private List<Pelamar> listPelamar;
	public TableModel(){
		
	}
	public TableModel(List<Pelamar> listPelamar){
		this.listPelamar = listPelamar;
	}
	public int getColumnCount(){
		return 7;
	}
	public int getRowCount(){
		try{
			return listPelamar.size();
		}catch(Exception er){
			return 0;
		}
	}
	public String getColumnName(int column){
		switch(column){
			case 0:
				return "ID";
			case 1:
				return "Nama";
			case 2:
				return "Tgl_Lahir";
			case 3:
				return "J_Kelamin";
			case 4:
				return "Pendidikan";
			case 5:
				return "Alamat";
			case 6:
				return "No_KTP";	
			default:
				return null;
		}
	}
	public Object getValueAt(int row, int column){
		switch(column){
			case 0:
				return listPelamar.get(row).getId();
			case 1:
				return listPelamar.get(row).getName();
			case 2:
				return listPelamar.get(row).getTTL();
			case 3:
				return listPelamar.get(row).getJenisK();
			case 4:
				return listPelamar.get(row).getPendidikan();
			case 5:
				return listPelamar.get(row).getAlamat();
			case 6:
				return listPelamar.get(row).getNoKtp();
			default:
				return null;
		}
	}
}