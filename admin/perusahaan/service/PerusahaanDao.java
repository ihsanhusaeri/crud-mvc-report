package projectsim.admin.perusahaan.service;

import projectsim.admin.perusahaan.model.*;
import projectsim.error.*;
import java.util.*;
public interface PerusahaanDao{
	public void save(Perusahaan perusahaan) throws DisnakerException;
	public void edit(Perusahaan perusahaan) throws DisnakerException;
	public void delete(String id) throws DisnakerException;
	public int createId()throws DisnakerException;
	public List<Perusahaan> getPerusahaan(String search, String kategori)throws DisnakerException;
	public List<Perusahaan> getAllData()throws DisnakerException;
}