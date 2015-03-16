package com.excilys.computerDataBase.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Connection objects.
 */
public class ConnectionFactory {
	
	/** The Constant DEFAULT_DATABASE. */
	private final static String DEFAULT_DATABASE = "computer-database-db" ;
	
	/** The Constant DEFAULT_USER. */
	private final static String DEFAULT_USER = "root" ;
	
	/** The Constant DEFAULT_PASSWORD. */
	private final static String DEFAULT_PASSWORD = "root";
	
	/**
	 * Creates a new Connection object.
	 *
	 * @param dataBase the data base
	 * @param user the user
	 * @param password the password
	 * @return the connection
	 */
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
	
	/**
	 * Creates a new Connection object.
	 *
	 * @return the connection
	 */
	public static Connection createConnection() {
		return createConnection(DEFAULT_DATABASE, DEFAULT_USER, DEFAULT_PASSWORD);
	}
	
}
