package dev.knittle.utilities;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;


public class JDBCConnection {
	//for Bank Application (Project 0)
	
private static Connection conn = null;
	
	//A little more detailed than typical getter
	public static Connection getConnection() {
		
		try {
			
			if(conn == null) {
				
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				Properties props = new Properties();
				
				InputStream input = JDBCConnection.class.getClassLoader().getResourceAsStream("connection.properties");
				props.load(input);
				
				String url = props.getProperty("url");
				String username = props.getProperty("username");
				String password = props.getProperty("password");
				
				conn = DriverManager.getConnection(url, username, password);
				
				return conn;
				
			} else {
				
				return conn;
				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		
		Connection conn1 = getConnection();
		
		System.out.println(conn1);
		
	}
	

}
