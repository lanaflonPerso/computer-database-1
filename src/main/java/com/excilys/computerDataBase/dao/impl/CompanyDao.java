package com.excilys.computerDataBase.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerDataBase.dao.CompanyDaoInterface;
import com.excilys.computerDataBase.exception.DaoException;
import com.excilys.computerDataBase.factory.ConnectionFactory;
import com.excilys.computerDataBase.model.Company;

/**
 * The Enum CompanyDao.
 */
public enum CompanyDao implements CompanyDaoInterface {

	/** The instance. */
	INSTANCE;

	/** The Constant PARAM_ID. */
	private static final String PARAM_ID = "id";

	/** The Constant PARAM_NAME. */
	private static final String PARAM_NAME = "name";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.excilys.computerDataBase.dao.DaoInterface#getAll()
	 */
	@Override
	public List<Company> getAll() {
		Statement statement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		List<Company> companies = null;		
		try {
			connection = ConnectionFactory.INSTANCE.createConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM company");
			companies = getCompanyList(resultSet);
		} catch (Exception e) {
			throw new DaoException(DaoException.CAN_NOT_GET_ELEMENT, e);
		} finally {
			closeConnection(statement, connection);
		}
		return companies;
	}

	/**
	 * Gets the company list.
	 *
	 * @param resultSet
	 *            the result set
	 * @return the company list
	 * @throws SQLException
	 *             the SQL exception
	 */
	private List<Company> getCompanyList(ResultSet resultSet)
			throws SQLException {
		List<Company> companies = new ArrayList<Company>();
		while (resultSet.next()) {
			long id = resultSet.getLong(PARAM_ID);
			String name = resultSet.getString(PARAM_NAME);
			companies.add(new Company(id, name));
		}
		return companies;
	}

	@Override
	public List<Company> getAll(Long from, Long to) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		List<Company> companies = null;
		try {
			connection = ConnectionFactory.INSTANCE.createConnection();
			preparedStatement = connection
					.prepareStatement("select * FROM company LIMIT ? OFFSET ?");
			preparedStatement.setLong(1, to - from);
			preparedStatement.setLong(2, from);
			resultSet = preparedStatement.executeQuery();
			companies = getCompanyList(resultSet);
		} catch (Exception e) {
			throw new DaoException(DaoException.CAN_NOT_GET_ELEMENT, e);
		} finally {
			closeConnection(preparedStatement, connection);
		}
		return companies;
	}

	@Override
	public Long getNumberOfElement() {
		Statement statement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		Long result = null;
		try {
			connection = ConnectionFactory.INSTANCE.createConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select count(*) from company");
			resultSet.next();
			result = Long.valueOf(resultSet.getString("count(*)"));
		} catch (Exception e) {
			throw new DaoException(DaoException.CAN_NOT_GET_ELEMENT, e);
		} finally {
			closeConnection(statement, connection);
		}
		return result;
	}

	/**
	 * Close connection.
	 *
	 * @param preparedStatement
	 *            the prepared statement
	 * @param connection
	 *            the connection
	 */
	private void closeConnection(Statement preparedStatement,
			Connection connection) {
		try {
			if (preparedStatement != null)
				preparedStatement.close();
		} catch (Exception e) {
			throw new DaoException(DaoException.CAN_NOT_CLOSE_STATEMENT, e);
		}
		try {
			if (connection != null)
				connection.close();
		} catch (Exception e) {
			throw new DaoException(DaoException.CAN_NOT_CLOSE_CONNECTION, e);
		}
	}

}
