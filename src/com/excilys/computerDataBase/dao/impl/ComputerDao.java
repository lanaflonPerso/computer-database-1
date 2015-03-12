package com.excilys.computerDataBase.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerDataBase.dao.ComputerDaoInterface;
import com.excilys.computerDataBase.entity.Computer;

/**
 * @author vgalloy
 *
 */
public class ComputerDao implements ComputerDaoInterface {
	private static final String PARAM_ID = "id";
	private static final String PARAM_NAME = "name";
	private static final String PARAM_INTRODUCED = "introduced";
	private static final String PARAM_DISCONTINUED = "discontinued";
	private static final String PARAM_COMPANY_ID = "company_id";
	private static ComputerDao computerDaoInstance = null;
	private Connection connection = null;

	public ComputerDao() {
		super();
	}

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
	public Computer create(Computer t) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection
					.prepareStatement(
							"INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?,?,?,?)",
							Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, t.getName());
			preparedStatement.setTimestamp(2, t.getIntroduced());
			preparedStatement.setTimestamp(3, t.getDiscontinued());
			preparedStatement.setLong(4, t.getCompany_id());
			preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys();
			resultSet.next();
			Long key = resultSet.getLong(1);
			t.setId(key);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement(preparedStatement);
		}
		return t;
	}

	@Override
	public void delete(Computer t) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection
					.prepareStatement("DELETE FROM computer WHERE id=?");
			preparedStatement.setLong(1, t.getId());
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement(preparedStatement);
		}

	}

	@Override
	public Computer update(Computer t) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
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
			closePreparedStatement(preparedStatement);
		}

		return null;
	}

	@Override
	public Computer get(long index) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
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
			e.printStackTrace();
		} finally {
			closePreparedStatement(preparedStatement);
		}
		return null;
	}

	@Override
	public List<Computer> selectAll() {
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM computer");

			if (resultSet != null) {
				return getComputerList(resultSet);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
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

	private void closePreparedStatement(PreparedStatement preparedStatement) {
		try {
			if (preparedStatement != null)
				preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public final static ComputerDao getInstance() {
		if (ComputerDao.computerDaoInstance == null) {
			ComputerDao.computerDaoInstance = new ComputerDao();
		}
		return ComputerDao.computerDaoInstance;
	}
}
