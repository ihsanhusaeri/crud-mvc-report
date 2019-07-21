package projectsim.admin.login.service.serviceimpl;

import projectsim.admin.login.service.*;
import projectsim.error.*;
import projectsim.admin.login.model.*;
import projectsim.connection.*;
import java.sql.*;

public class LoginDaoImpl implements LoginDao{
	private Connection connection;
	private String query = "Select * from admin where username=? and password=?";
	private PreparedStatement statement;
	public LoginDaoImpl(){
		try{
			connection = ConnectionDatabase.getConnection();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	public boolean validasiAdmin(Admin admin)throws DisnakerException{
		statement = null;
		try{
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(query);
			statement.setString(1, admin.getName());
			statement.setString(2, admin.getPassword());
			ResultSet set = statement.executeQuery();
			if(set.next())
				return true;
			connection.commit();
			return false;
		}catch(SQLException e){
			try{
				connection.rollback();
			}catch(SQLException exc){
				
			}
			throw new DisnakerException(e.getMessage());
		}finally{
			try{
				connection.setAutoCommit(true);
			}catch(SQLException e){
				
			}
			if(statement != null){
				try{
					statement.close();
				}catch(SQLException exx){
					
				}
			}
		}
	}
}