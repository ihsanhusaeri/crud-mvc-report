package projectsim.connection;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.*;
public class ConnectionDatabase{
	private static Connection connection;
	public ConnectionDatabase(){
		
	}
	public ConnectionDatabase(Connection connection){
		this.connection = connection;
	}
	public static Connection getConnection() throws SQLException {
		try{
			if(connection==null){
				MysqlDataSource dataSource = new MysqlDataSource();
				dataSource.setUrl("jdbc:mysql://localhost:3306/disnaker");
				dataSource.setUser("root");
				dataSource.setPassword("");
				try{
					connection = dataSource.getConnection();
				}catch(SQLException err){
					err.printStackTrace();
				}
			}
		}catch(Exception error){
			error.printStackTrace();
		}
		return connection;
	}
}