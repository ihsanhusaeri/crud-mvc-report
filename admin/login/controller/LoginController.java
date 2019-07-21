package projectsim.admin.login.controller;

import javax.swing.*;
import projectsim.admin.login.view.*;
import projectsim.admin.login.model.*;
import projectsim.admin.login.service.serviceimpl.*;
import projectsim.admin.login.service.*;
import projectsim.error.*;

public class LoginController{
	private LoginDao loginDao;
	private LoginView loginView;
	private boolean login;
	public LoginController(LoginView loginView){
		this.loginView = loginView;
		loginDao = new LoginDaoImpl();
	}
	public void validasiAdmin(){
		Admin admin = new Admin();
		String userName = loginView.getTxtName().getText();
		String passsword = loginView.getTxtPassword().getText();
		
		admin.setName(userName);
		admin.setPassword(passsword);
		try{
			login = loginDao.validasiAdmin(admin);
		}catch(DisnakerException e){
			
		}
		if(login){
			JOptionPane.showMessageDialog(null, "Berhasil Login!", "Login", JOptionPane.INFORMATION_MESSAGE);
		}else{
			JOptionPane.showMessageDialog(null, "Gagal Login!", "Login", JOptionPane.ERROR_MESSAGE);
		}
	}
}