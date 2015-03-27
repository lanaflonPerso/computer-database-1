/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.factory;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.computerdatabase.exception.DaoException;

/**
 * A factory for creating Connection objects.
 */
@Service
public class ConnectionFactory {
	@Autowired
	private DataSource dataSource;
	private ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
	
	public ConnectionFactory() {
		super();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			throw new RuntimeException("can not load Driver", e);
		}
	}

	public Connection getConnection() {
		try {
			if (threadLocal.get() == null || threadLocal.get().isClosed()) {
				threadLocal.set(dataSource.getConnection());
			}
			return threadLocal.get();
		} catch (SQLException e2) {
			throw new DaoException(DaoException.CAN_NOT_CREATE_CONNECTION, e2);
		}
	}

	public Connection startTransaction() {
		Connection connection = getConnection();
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			new DaoException(DaoException.CAN_NOT_CLOSE_CONNECTION, e);
		}
		return connection;
	}
	
	public void closeConnection() {
		closeConnection(getConnection());
	}

	public void commit() {
		try {
			getConnection().commit();
		} catch (SQLException e) {
			throw new DaoException(DaoException.CAN_NOT_COMMIT_TRANSACTION);
		}
	}
	
	public static void closeConnection(Connection connection) {
		try {
			if (connection != null && !connection.getAutoCommit()) {
				connection.close();
			}
		} catch (SQLException e) {
			throw new DaoException(DaoException.CAN_NOT_CLOSE_CONNECTION, e);
		}
	}

	public void forcedCloseConnection() {
		try {
			if (getConnection() != null) {
				getConnection().close();
			}
		} catch (SQLException e) {
			throw new DaoException(DaoException.CAN_NOT_CLOSE_CONNECTION, e);
		}
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
