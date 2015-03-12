package com.excilys.computerDataBase.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerDataBase.dao.CompanyDaoInterface;
import com.excilys.computerDataBase.entity.Company;

public class CompanyDao implements CompanyDaoInterface{
	private static final String PARAM_ID = "id";
	private static final String PARAM_NAME = "name";
	private static CompanyDao companyDaoInstance = null;
	private Connection connection = null;
	
	@Override
	public void openConnection(String dataBase, String user, String password)
			throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			System.out.println("Driver problem");
			e.printStackTrace();
		}

		connection = DriverManager.getConnection("jdbc:mysql://localhost/"
				+ dataBase + "?user=" + user + "&password=" + password
				+ "&zeroDateTimeBehavior=convertToNull");
	}

	@Override
	public void closeConnection() {
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}


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
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM company");

			if (resultSet != null) {
				return getCompanyList(resultSet);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeStatement(statement);
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

	private void closeStatement(Statement statement) {
		try {
			if (statement != null)
				statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public final static CompanyDao getInstance() {
		if (CompanyDao.companyDaoInstance == null) {
			CompanyDao.companyDaoInstance = new CompanyDao();
		}
		return CompanyDao.companyDaoInstance;
	}
	
}
