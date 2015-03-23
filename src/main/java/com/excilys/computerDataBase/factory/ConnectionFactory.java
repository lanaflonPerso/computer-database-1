package com.excilys.computerDataBase.factory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.excilys.computerDataBase.exception.DaoException;

/**
 * A factory for creating Connection objects.
 */
public enum ConnectionFactory {
	INSTANCE;

	private Properties properties;
	private String url;

	private ConnectionFactory() {

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			throw new RuntimeException("can not load Driver", e);
		}

		properties = new Properties();
		try {

			final InputStream is = ConnectionFactory.class.getClassLoader()
					.getResourceAsStream("config.properties");
			properties.load(is);
			url = properties.getProperty("url");
		} catch (Exception e) {
			throw new RuntimeException("can not load config.properties", e);
		}

	}

	public Connection createConnection() {
		try {
			return DriverManager.getConnection(url, properties);
		} catch (SQLException e) {
			throw new DaoException(DaoException.CAN_NOT_CREATE_CONNECTION);
		}
	}
}
