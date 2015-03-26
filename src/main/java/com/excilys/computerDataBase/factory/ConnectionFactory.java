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
					.getResourceAsStream("boneCPConfig.properties");
			properties.load(is);
		} catch (Exception e) {
			throw new RuntimeException("can not load config.properties", e);
		}

		BoneCPConfig config;
		try {
			config = new BoneCPConfig(properties);
		} catch (Exception e1) {
			throw new DaoException(DaoException.CAN_NOT_CREATE_CONNECTION, e1);
		}
		
		try {
			connectionPool = new BoneCP(config);
		} catch (SQLException e) {
			throw new RuntimeException("can not create Connection pool", e);
		}

	}

	public Connection createConnection() {
		try {
			return connectionPool.getConnection();
		} catch (SQLException e2) {
			throw new DaoException(DaoException.CAN_NOT_CREATE_CONNECTION, e2);
		}
	}
}
