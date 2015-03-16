package com.excilys.computerDataBase.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerDataBase.dao.ComputerDaoInterface;
import com.excilys.computerDataBase.entity.Computer;
import com.excilys.computerDataBase.exception.GetException;
import com.excilys.computerDataBase.exception.UnableToInsertElementException;
import com.excilys.computerDataBase.factory.ConnectionFactory;

import java.sql.Connection;

/**
 * @author vgalloy
 *
 */
public enum ComputerDao implements ComputerDaoInterface {

	/** The instance. */
	INSTANCE;

	private static final String PARAM_ID = "id";
	private static final String PARAM_NAME = "name";
	private static final String PARAM_INTRODUCED = "introduced";
	private static final String PARAM_DISCONTINUED = "discontinued";
	private static final String PARAM_COMPANY_ID = "company_id";

	@Override
	public Computer create(Computer t) {
		if (t == null) {
			throw new UnableToInsertElementException(
					UnableToInsertElementException.NULL_COMPUTER);
		}
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		try {
			connection = ConnectionFactory.createConnection();
			preparedStatement = connection
					.prepareStatement(
							"INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?,?,?,?)",
							Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, t.getName());
			preparedStatement.setTimestamp(2, t.getIntroduced());
			preparedStatement.setTimestamp(3, t.getDiscontinued());
			if (t.getCompany_id() == null) {
				throw new UnableToInsertElementException(
						UnableToInsertElementException.NULL_COMPANY_ID);
			}
			preparedStatement.setLong(4, t.getCompany_id());
			preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys();
			resultSet.next();
			Long key = resultSet.getLong(1);
			t.setId(key);

		} catch (SQLException e) {
			throw new UnableToInsertElementException(
					UnableToInsertElementException.CAN_NOT_INSERT, e);
		} finally {
			closeConnection(preparedStatement, connection);
		}
		return t;
	}

	@Override
	public void delete(Computer t) {
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		try {
			connection = ConnectionFactory.createConnection();
			preparedStatement = connection
					.prepareStatement("DELETE FROM computer WHERE id=?");
			preparedStatement.setLong(1, t.getId());
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(preparedStatement, connection);
		}

	}

	@Override
	public Computer update(Computer t) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		try {
			connection = ConnectionFactory.createConnection();
			preparedStatement = connection
					.prepareStatement("UPDATE computer SET name=?, introduced=?, discontinued=?, company_id=? WHERE id=?");
			preparedStatement.setString(1, t.getName());
			preparedStatement.setTimestamp(2, t.getIntroduced());
			preparedStatement.setTimestamp(3, t.getDiscontinued());
			preparedStatement.setLong(4, t.getCompany_id());
			preparedStatement.setLong(5, t.getId());
			preparedStatement.executeUpdate();
			resultSet = preparedStatement.getResultSet();
			if (resultSet != null) {
				return getComputerList(resultSet).get(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(preparedStatement, connection);
		}

		return null;
	}

	@Override
	public Computer get(long index) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		try {
			connection = ConnectionFactory.createConnection();
			preparedStatement = connection
					.prepareStatement("SELECT * FROM computer WHERE id=?");
			preparedStatement.setLong(1, index);
			resultSet = preparedStatement.executeQuery();

			if (resultSet != null) {
				List<Computer> computers = getComputerList(resultSet);
				if (computers.size() >= 1) {
					return computers.get(0);
				}
			}

		} catch (SQLException e) {
			throw new GetException(GetException.CAN_NOT_GET_COMPUTER);
		} finally {
			closeConnection(preparedStatement, connection);
		}
		throw new GetException(GetException.NO_ITEM_FOUND);
	}

	@Override
	public List<Computer> selectAll() {
		Statement statement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		try {
			connection = ConnectionFactory.createConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM computer");

			if (resultSet != null) {
				return getComputerList(resultSet);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(statement, connection);
		}
		return new ArrayList<Computer>();
	}

	private List<Computer> getComputerList(ResultSet resultSet)
			throws SQLException {
		List<Computer> computers = new ArrayList<Computer>();
		while (resultSet.next()) {
			long id = resultSet.getLong(PARAM_ID);
			String name = resultSet.getString(PARAM_NAME);
			Timestamp introduced = resultSet.getTimestamp(PARAM_INTRODUCED);
			Timestamp discontinued = resultSet.getTimestamp(PARAM_DISCONTINUED);
			long companyId = resultSet.getLong(PARAM_COMPANY_ID);
			computers.add(new Computer(id, name, introduced, discontinued,
					companyId));
		}
		return computers;
	}

	private void closeConnection(Statement preparedStatement,
			Connection connection) {
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
