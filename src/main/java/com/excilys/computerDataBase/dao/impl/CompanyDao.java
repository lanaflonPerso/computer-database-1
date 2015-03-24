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
import com.excilys.computerDataBase.model.Computer;
import com.excilys.computerDataBase.util.DaoUtil;

/**
 * The Enum CompanyDao.
 */
public enum CompanyDao implements CompanyDaoInterface {
	INSTANCE;

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
			companies = DaoUtil.getCompanyList(resultSet);
		} catch (SQLException e) {
			throw new DaoException(DaoException.CAN_NOT_GET_ELEMENT, e);
		} finally {
			DaoUtil.closeConnection(statement, connection);
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
			companies = DaoUtil.getCompanyList(resultSet);
		} catch (SQLException e) {
			throw new DaoException(DaoException.CAN_NOT_GET_ELEMENT, e);
		} finally {
			DaoUtil.closeConnection(preparedStatement, connection);
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
		} catch (SQLException e) {
			throw new DaoException(DaoException.CAN_NOT_GET_ELEMENT, e);
		} finally {
			DaoUtil.closeConnection(statement, connection);
		}
		return result;
	}

	@Override
	public void delete(Long id) {
		Connection connection = null;
		try {
			connection = ConnectionFactory.INSTANCE.createConnection();
			connection.setAutoCommit(false);
			List<Computer> computers = getComputerWithCompanyId(connection, id);
			for(Computer computer : computers) {
				ComputerDao.INSTANCE.delete(computer.getId());
			}
			deleteCompany(connection, id);
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new DaoException(DaoException.CAN_NOT_ROLLBACK_TRANSACTION, e);
			}
			throw new DaoException(DaoException.CAN_NOT_DELETE_ELEMENT, e);
		} finally {
			DaoUtil.closeConnection(connection);
		}
	}

	private void deleteCompany(Connection connection, Long id) {
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

	private List<Computer> getComputerWithCompanyId(Connection connection, Long id) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Computer> computers = null;
		try {
			preparedStatement = connection.prepareStatement("select * from computer compu LEFT JOIN company compa ON compu.company_id = compa.id WHERE compa.id = ?");
			preparedStatement.setLong(1, id);
			resultSet = preparedStatement.executeQuery();
			computers = DaoUtil.getComputerList(resultSet);
		} catch (SQLException e) {
			throw new DaoException(DaoException.CAN_NOT_GET_ELEMENT, e);
		} finally {
			DaoUtil.closeStatement(preparedStatement);
		}
		return computers;
	}
	
}
