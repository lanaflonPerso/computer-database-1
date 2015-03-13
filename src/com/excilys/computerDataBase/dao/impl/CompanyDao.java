package com.excilys.computerDataBase.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerDataBase.dao.CompanyDaoInterface;
import com.excilys.computerDataBase.entity.Company;
import com.excilys.computerDataBase.factory.ConnectionFactory;

public enum CompanyDao implements CompanyDaoInterface {
	INSTANCE;
	
	private static final String PARAM_ID = "id";
	private static final String PARAM_NAME = "name";

	@Override
	public Company create(Company t) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void delete(Company t) {
		throw new UnsupportedOperationException();

	}

	@Override
	public Company update(Company t) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Company get(long index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Company> selectAll() {

		Statement statement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		try {
			connection = ConnectionFactory.createConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM company");

			if (resultSet != null) {
				return getCompanyList(resultSet);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(statement, connection);
		}
		return new ArrayList<Company>();
	}

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

	private void closeConnection(Statement preparedStatement, Connection connection) {
		try {
			if (preparedStatement != null)
				preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
