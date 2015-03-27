/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.excilys.computerdatabase.exception.DaoException;
import com.excilys.computerdatabase.factory.ConnectionFactory;

public class DaoUtil {
	
	public static void close(Statement statement, Connection connection) {
		closeStatement(statement);
		ConnectionFactory.closeConnection(connection);
	}
	
	public static void closeStatement(Statement statement) {
		try {
			if (statement != null) {
				statement.close();
			}
		} catch (SQLException e) {
			throw new DaoException(DaoException.CAN_NOT_CLOSE_STATEMENT, e);
		}
	}
}
