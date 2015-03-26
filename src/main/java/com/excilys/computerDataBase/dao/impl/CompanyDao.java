/**
 * @Author Vincent Galloy
 */
package com.excilys.computerDataBase.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.excilys.computerDataBase.dao.CompanyDaoInterface;
import com.excilys.computerDataBase.exception.DaoException;
import com.excilys.computerDataBase.factory.ConnectionFactory;
import com.excilys.computerDataBase.model.Company;
import com.excilys.computerDataBase.sort.SortCriteria;
import com.excilys.computerDataBase.util.DaoUtil;

/**
 * The Enum CompanyDao.
 */
public enum CompanyDao implements CompanyDaoInterface {
	INSTANCE;
	
	ConnectionFactory connectionFactory = ConnectionFactory.INSTANCE;
	@Override
	public void create(Connection connection, Company t) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection
					.prepareStatement(
							"INSERT INTO company (name) VALUES (?)",
							Statement.RETURN_GENERATED_KEYS);
			if(t.getName().trim().isEmpty()) {
				throw new NullPointerException();
			}
			preparedStatement.setString(1, t.getName().trim());
			preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys();
			resultSet.next();
			Long key = resultSet.getLong(1);
			t.setId(key);
		} catch (SQLException e) {
			throw new DaoException(DaoException.CAN_NOT_INSERT_ELEMENT, e);
		} finally {
			DaoUtil.closeStatement(preparedStatement);
		}
		
	}
	
	@Override
	public List<Company> getAll(SortCriteria sortCriteria) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		List<Company> companies = null;
		try {
			connection = connectionFactory.createConnection();
			String request = "SELECT * FROM company ORDER BY " + sortCriteria.toString();
			preparedStatement = connection.prepareStatement(request);
			resultSet = preparedStatement.executeQuery();
			companies = DaoUtil.getCompanyList(resultSet);
		} catch (SQLException e) {
			throw new DaoException(DaoException.CAN_NOT_GET_ELEMENT, e);
		} finally {
			DaoUtil.close(preparedStatement, connection);
		}
		return companies;
	}
	
	@Override
	public List<Company> getAll(Long from, Long to, SortCriteria sortCriteria) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		List<Company> companies = null;
		try {
			connection = connectionFactory.createConnection();
			String request = "select * FROM company ORDER BY " + sortCriteria.toString() + " LIMIT ? OFFSET ?";
			preparedStatement = connection.prepareStatement(request);
			preparedStatement.setLong(1, to - from);
			preparedStatement.setLong(2, from);
			resultSet = preparedStatement.executeQuery();
			companies = DaoUtil.getCompanyList(resultSet);
		} catch (SQLException e) {
			throw new DaoException(DaoException.CAN_NOT_GET_ELEMENT, e);
		} finally {
			DaoUtil.close(preparedStatement, connection);
		}
		return companies;
	}

	@Override
	public Long getNumberOfElement(Connection connection) {
		Statement statement = null;
		ResultSet resultSet = null;
		Long result = null;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select count(*) from company");
			resultSet.next();
			result = Long.valueOf(resultSet.getString("count(*)"));
		} catch (SQLException e) {
			throw new DaoException(DaoException.CAN_NOT_GET_ELEMENT, e);
		} finally {
			DaoUtil.closeStatement(statement);
		}
		return result;
	}
	
	@Override
	public void delete(Connection connection, Long id) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection
					.prepareStatement("DELETE FROM company WHERE id=?");
			preparedStatement.setLong(1, id);
			preparedStatement.execute();
		} catch (SQLException e) {
			throw new DaoException(DaoException.CAN_NOT_DELETE_ELEMENT, e);
		} finally {
			DaoUtil.closeStatement(preparedStatement);
		}
	}

	@Override
	public Company getById(Connection connection, Long id) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Company company = null;
		try {
			preparedStatement = connection
					.prepareStatement("select * from company WHERE id=?");
			preparedStatement.setLong(1, id);
			resultSet = preparedStatement.executeQuery();
			List<Company> companies = DaoUtil.getCompanyList(resultSet);
			company = companies.get(0);
		} catch (Exception e) {
			throw new DaoException(DaoException.CAN_NOT_GET_ELEMENT, e);
		} finally {
			DaoUtil.closeStatement(preparedStatement);
		}
		return company;
	}
	
}
