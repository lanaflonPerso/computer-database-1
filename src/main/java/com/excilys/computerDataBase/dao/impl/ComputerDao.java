package com.excilys.computerDataBase.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

import com.excilys.computerDataBase.dao.ComputerDaoInterface;
import com.excilys.computerDataBase.exception.DaoException;
import com.excilys.computerDataBase.factory.ConnectionFactory;
import com.excilys.computerDataBase.model.Computer;
import com.excilys.computerDataBase.util.DaoUtil;

/**
 * The Enum ComputerDao.
 *
 * @author vgalloy
 */
public enum ComputerDao implements ComputerDaoInterface {

	INSTANCE;

	@Override
	public void create(Computer t) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		try {
			connection = ConnectionFactory.INSTANCE.createConnection();
			preparedStatement = connection
					.prepareStatement(
							"INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?,?,?,?)",
							Statement.RETURN_GENERATED_KEYS);
			if(t.getName().trim().isEmpty()) {
				throw new NullPointerException();
			}
			preparedStatement.setString(1, t.getName().trim());

			if (t.getIntroduced() != null) {
				preparedStatement.setTimestamp(2,
						Timestamp.valueOf(t.getIntroduced()));
			} else {
				preparedStatement.setNull(2, java.sql.Types.TIMESTAMP);
			}

			if (t.getDiscontinued() != null) {
				preparedStatement.setTimestamp(3,
						Timestamp.valueOf(t.getDiscontinued()));
			} else {
				preparedStatement.setNull(3, java.sql.Types.TIMESTAMP);
			}

			if (t.getCompany().getId() != null) {
				preparedStatement.setLong(4, t.getCompany().getId());
			} else {
				preparedStatement.setNull(4, java.sql.Types.BIGINT);
			}
			preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys();
			resultSet.next();
			Long key = resultSet.getLong(1);
			t.setId(key);
		} catch (SQLException e) {
			throw new DaoException(DaoException.CAN_NOT_INSERT_ELEMENT, e);
		} finally {
			DaoUtil.closeConnection(preparedStatement, connection);
		}
	}

	@Override
	public void delete(Long computerId) {
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		try {
			connection = ConnectionFactory.INSTANCE.createConnection();
			preparedStatement = connection
					.prepareStatement("DELETE FROM computer WHERE id=?");
			preparedStatement.setLong(1, computerId);
			preparedStatement.execute();
		} catch (SQLException e) {
			throw new DaoException(DaoException.CAN_NOT_DELETE_ELEMENT, e);
		} finally {
			DaoUtil.closeConnection(preparedStatement, connection);
		}
	}

	@Override
	public void update(Computer t) {
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		try {
			connection = ConnectionFactory.INSTANCE.createConnection();
			preparedStatement = connection
					.prepareStatement("UPDATE computer SET name=?, introduced=?, discontinued=?, company_id=? WHERE id=?");
			
			if(t.getName().trim().isEmpty()) {
				throw new NullPointerException();
			}
			preparedStatement.setString(1, t.getName().trim());

			if (t.getIntroduced() != null) {
				preparedStatement.setTimestamp(2,
						Timestamp.valueOf(t.getIntroduced()));
			} else {
				preparedStatement.setNull(2, java.sql.Types.TIMESTAMP);
			}

			if (t.getDiscontinued() != null) {
				preparedStatement.setTimestamp(3,
						Timestamp.valueOf(t.getDiscontinued()));
			} else {
				preparedStatement.setNull(3, java.sql.Types.TIMESTAMP);
			}

			if (t.getCompany().getId() != null) {
				preparedStatement.setLong(4, t.getCompany().getId());
			} else {
				preparedStatement.setNull(4, java.sql.Types.BIGINT);
			}

			preparedStatement.setLong(5, t.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(DaoException.CAN_NOT_UPDATE_ELEMENT, e);
		} finally {
			DaoUtil.closeConnection(preparedStatement, connection);
		}
	}

	@Override
	public Computer getById(Long index) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		Computer computer = null;
		try {
			connection = ConnectionFactory.INSTANCE.createConnection();
			preparedStatement = connection
					.prepareStatement("select * from computer compu LEFT JOIN company compa ON compu.company_id = compa.id WHERE compu.id=?");
			preparedStatement.setLong(1, index);
			resultSet = preparedStatement.executeQuery();
			List<Computer> computers = DaoUtil.getComputerList(resultSet);
			computer = computers.get(0);
		} catch (Exception e) {
			throw new DaoException(DaoException.CAN_NOT_GET_ELEMENT, e);
		} finally {
			DaoUtil.closeConnection(preparedStatement, connection);
		}
		return computer;
	}

	@Override
	public List<Computer> getAll() {
		Statement statement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		List<Computer> computers = null;
		try {
			connection = ConnectionFactory.INSTANCE.createConnection();
			statement = connection.createStatement();
			resultSet = statement
					.executeQuery("select * from computer compu LEFT JOIN company compa ON compu.company_id = compa.id");
			computers = DaoUtil.getComputerList(resultSet);
		} catch (SQLException e) {
			throw new DaoException(DaoException.CAN_NOT_GET_ELEMENT, e);
		} finally {
			DaoUtil.closeConnection(statement, connection);
		}
		return computers;
	}

	@Override
	public List<Computer> getAll(Long from, Long to) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		List<Computer> computers = null;
		try {
			connection = ConnectionFactory.INSTANCE.createConnection();
			preparedStatement = connection
					.prepareStatement("select * from computer compu LEFT JOIN company compa ON compu.company_id = compa.id LIMIT ? OFFSET ?");
			preparedStatement.setLong(1, to - from);
			preparedStatement.setLong(2, from);
			resultSet = preparedStatement.executeQuery();
			computers = DaoUtil.getComputerList(resultSet);
		} catch (SQLException e) {
			throw new DaoException(DaoException.CAN_NOT_GET_ELEMENT, e);
		} finally {
			DaoUtil.closeConnection(preparedStatement, connection);
		}
		return computers;
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
			resultSet = statement.executeQuery("select count(*) from computer");
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
	public List<Computer> getNameContains(String string) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		List<Computer> computers = null;
		try {
			connection = ConnectionFactory.INSTANCE.createConnection();
			preparedStatement = connection
					.prepareStatement("select * from computer compu LEFT JOIN company compa ON compu.company_id = compa.id WHERE compu.name LIKE ? or compa.name LIKE ?");
			preparedStatement.setString(1, "%" + string + "%");
			preparedStatement.setString(2, "%" + string + "%");
			resultSet = preparedStatement.executeQuery();
			computers = DaoUtil.getComputerList(resultSet);
		} catch (SQLException e) {
			throw new DaoException(DaoException.CAN_NOT_GET_ELEMENT, e);
		} finally {
			DaoUtil.closeConnection(preparedStatement, connection);
		}
		return computers;
	}

}
