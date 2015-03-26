/**
 * @Author Vincent Galloy
 */
package com.excilys.computerDataBase.factory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.excilys.computerDataBase.exception.DaoException;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

/**
 * A factory for creating Connection objects.
 */
public enum ConnectionFactory {
	INSTANCE;

	private Properties properties;
	private String url;
	private BoneCP connectionPool;

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

		BoneCPConfig config = new BoneCPConfig();
		config.setJdbcUrl(url);
		config.setUsername(properties.getProperty("user"));
		config.setPassword(properties.getProperty("password"));
		config.setMinConnectionsPerPartition(5);
		config.setMaxConnectionsPerPartition(10);
		config.setPartitionCount(2);

		try {
			connectionPool = new BoneCP(config);
		} catch (SQLException e) {
			throw new RuntimeException("can not create Connection pool", e);
		}

	}

	public Connection createConnection() {
		try {
			return connectionPool.getConnection();
		} catch (SQLException e) {
			throw new DaoException(DaoException.CAN_NOT_CREATE_CONNECTION);
		}
	}
}
