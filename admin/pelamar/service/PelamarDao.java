package projectsim.admin.pelamar.service;

import java.util.*;
import projectsim.admin.pelamar.model.*;
import projectsim.error.*;

public interface PelamarDao{
	public void save(Pelamar pelamar) throws DisnakerException;
	public void edit(Pelamar pelamar) throws DisnakerException;
	public void delete(int id) throws DisnakerException;
	public int createId()throws DisnakerException;
	public List<Pelamar> getPelamar(String search, String kategori) throws DisnakerException;
	public List<Pelamar> getAllData() throws DisnakerException;
}