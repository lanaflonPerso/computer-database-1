package com.excilys.computerDataBase.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private final static String DEFAULT_DATABASE = "computer-database-db" ;
	private final static String DEFAULT_USER = "root" ;
	private final static String DEFAULT_PASSWORD = "root";
	public static Connection createConnection(String dataBase, String user, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			System.out.println("Driver problem");	
			e.printStackTrace();
		}
		
		
		Connection connection = null;
		try {
			StringBuilder stringBuilder = new StringBuilder("jdbc:mysql://localhost/");
			stringBuilder.append(dataBase);
			stringBuilder.append("?user=").append(user);
			stringBuilder.append("&password=").append(password);
			stringBuilder.append("&zeroDateTimeBehavior=convertToNull");
			connection = DriverManager.getConnection(stringBuilder.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public static Connection createConnection() {
		return createConnection(DEFAULT_DATABASE, DEFAULT_USER, DEFAULT_PASSWORD);
	}
	
}
