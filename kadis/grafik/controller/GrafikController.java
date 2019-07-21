package projectsim.kadis.grafik.controller;

import projectsim.connection.*;
import projectsim.error.*;
import projectsim.kadis.grafik.view.*;
import projectsim.kadis.grafik.model.*;
import projectsim.kadis.grafik.service.*;
import java.util.*;

public class GrafikController{
	private GrafikView grafikView;
	private GrafikDao grafikDao = new GrafikDao();
	private Map<Integer, List<Lowongan>> lowongan;
	private Map<Integer, List<JumlahPelamar>> pelamar;
	private Map<Long, List<Long>> karyawan;
	public GrafikController(GrafikView grafikView){
		this.grafikView=grafikView;
	}
	public void setValueJumlahLowongan(){
		try{
			lowongan = grafikDao.getLowongan();
			Set<Integer> keys = lowongan.keySet();
			Iterator<Integer> iterator = keys.iterator();
			while(iterator.hasNext()){
				Integer key = iterator.next();
				List<Lowongan> listLowongan = lowongan.get(key);
				for(int i=0; i < listLowongan.size(); i++){
					Lowongan l = listLowongan.get(i);
					grafikView.getDatasetLowongan().setValue(l.getJumlahLowongan(), "", String.valueOf(key));
					//System.out.println("L di controller "+i+" "+l.getJumlahLowongan());
				}
			}
			/*grafikView.getDatasetPelamar().setValue(0.0, "Laki-Laki", "");
			grafikView.getDatasetPelamar().setValue(0.0, "Perempuan", "");*/
		}catch(DisnakerException e){
			e.printStackTrace();
		}
	}
	public void setValueJumlahKaryawan(){
		try{
			karyawan = grafikDao.getKaryawan();
			Set<Long> keys = karyawan.keySet();
			Iterator<Long> iterator = keys.iterator();
			while(iterator.hasNext()){
				Long key = iterator.next();
				List<Long> listKaryawan = karyawan.get(key);
				for(int i=0; i < listKaryawan.size(); i++){
					Long l = listKaryawan.get(i);
					grafikView.getDatasetKaryawan().setValue(l, "", String.valueOf(key));
					/*System.out.println("L di controller "+i+" "+l);
					System.out.println("Key di controller "+i+" "+String.valueOf(key));*/
				}
			}
			/*grafikView.getDatasetPelamar().setValue(0.0, "Laki-Laki", "");
			grafikView.getDatasetPelamar().setValue(0.0, "Perempuan", "");*/
		}catch(DisnakerException e){
			e.printStackTrace();
		}
	}
	public void setValueJumlahPelamar(){
		try{
			pelamar = grafikDao.getJumlahPelamar();
			Set<Integer> keys = pelamar.keySet();
			Iterator<Integer> iterator = keys.iterator();
			while(iterator.hasNext()){
				Integer key = iterator.next();
				List<JumlahPelamar> listPelamar = pelamar.get(key);
				for(int i=0; i < listPelamar.size(); i++){
					JumlahPelamar j = listPelamar.get(i);
					grafikView.getDatasetPelamar().setValue(j.getJumlahLaki(), "Laki-Laki", String.valueOf(key));
					grafikView.getDatasetPelamar().setValue(j.getJumlahPerempuan(), "Perempuan", String.valueOf(key));
				}
			}
			//grafikView.getDatasetLowongan().setValue(0.0, "", "");
		}catch(DisnakerException e){
			e.printStackTrace();
		}
	}
}

