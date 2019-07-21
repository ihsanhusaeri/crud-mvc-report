package projectsim.admin.login.service;

import projectsim.error.*;
import projectsim.admin.login.model.*;
import projectpbo.login.error.*;
public interface LoginDao{
	public boolean validasiAdmin(Admin admin) throws DisnakerException;
}