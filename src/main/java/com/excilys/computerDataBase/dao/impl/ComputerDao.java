/**
 * @Author Vincent Galloy
 */
package com.excilys.computerDataBase.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

import com.excilys.computerDataBase.dao.ComputerDaoInterface;
import com.excilys.computerDataBase.dao.sort.SortCriteria;
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
	public void create(Connection connection, Computer t) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection
					.prepareStatement(
							"INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?,?,?,?)",
							Statement.RETURN_GENERATED_KEYS);
			if (t.getName().trim().isEmpty()) {
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
			DaoUtil.closeStatement(preparedStatement);
		}
	}

	@Override
	public void delete(Connection connection, Long computerId) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection
					.prepareStatement("DELETE FROM computer WHERE id=?");
			preparedStatement.setLong(1, computerId);
			preparedStatement.execute();
		} catch (SQLException e) {
			throw new DaoException(DaoException.CAN_NOT_DELETE_ELEMENT, e);
		} finally {
			DaoUtil.closeStatement(preparedStatement);
		}
	}

	@Override
	public void update(Connection connection, Computer t) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection
					.prepareStatement("UPDATE computer SET name=?, introduced=?, discontinued=?, company_id=? WHERE id=?");

			if (t.getName().trim().isEmpty()) {
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
			DaoUtil.closeStatement(preparedStatement);
		}
	}

	@Override
	public Computer getById(Connection connection, Long index) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Computer computer = null;
		try {
			preparedStatement = connection
					.prepareStatement("select * from computer compu LEFT JOIN company compa ON compu.company_id = compa.id WHERE compu.id=?");
			preparedStatement.setLong(1, index);
			resultSet = preparedStatement.executeQuery();
			List<Computer> computers = DaoUtil.getComputerList(resultSet);
			computer = computers.get(0);
		} catch (Exception e) {
			throw new DaoException(DaoException.CAN_NOT_GET_ELEMENT, e);
		} finally {
			DaoUtil.closeStatement(preparedStatement);
		}
		return computer;
	}

	@Override
	public List<Computer> getAll(SortCriteria sortCriteria) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		List<Computer> computers = null;
		try {
			connection = ConnectionFactory.INSTANCE.createConnection();
			String request = "select * from computer compu LEFT JOIN company compa ON compu.company_id = compa.id ORDER BY " + sortCriteria.toString();
			preparedStatement = connection
					.prepareStatement(request);
			resultSet = preparedStatement.executeQuery();
			computers = DaoUtil.getComputerList(resultSet);
		} catch (SQLException e) {
			throw new DaoException(DaoException.CAN_NOT_GET_ELEMENT, e);
		} finally {
			DaoUtil.close(preparedStatement, connection);
		}
		return computers;
	}

	@Override
	public List<Computer> getAll(Long from, Long to, SortCriteria sortCriteria) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		List<Computer> computers = null;
		try {
			connection = ConnectionFactory.INSTANCE.createConnection();
			String request = "select * from computer compu LEFT JOIN company compa ON compu.company_id = compa.id ORDER BY " + sortCriteria.toString() + " LIMIT ? OFFSET ? ";
			preparedStatement = connection
					.prepareStatement(request);
			preparedStatement.setLong(1, to - from);
			preparedStatement.setLong(2, from);
			resultSet = preparedStatement.executeQuery();
			computers = DaoUtil.getComputerList(resultSet);
		} catch (SQLException e) {
			throw new DaoException(DaoException.CAN_NOT_GET_ELEMENT, e);
		} finally {
			DaoUtil.close(preparedStatement, connection);
		}
		return computers;
	}

	@Override
	public Long getNumberOfElement(Connection connection) {
		Statement statement = null;
		ResultSet resultSet = null;
		Long result = null;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select count(*) from computer");
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
	public List<Computer> getNameContains(String string, Long from, Long to,
			SortCriteria sortCriteria) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		List<Computer> computers = null;
		try {
			connection = ConnectionFactory.INSTANCE.createConnection();
			String request = "SELECT * FROM computer compu LEFT JOIN company compa ON compu.company_id = compa.id WHERE compu.name LIKE ? or compa.name LIKE ? ORDER BY " + sortCriteria.toString() + " LIMIT ? OFFSET ?";
			preparedStatement = connection
					.prepareStatement(request);
			preparedStatement.setString(1, "%" + string + "%");
			preparedStatement.setString(2, "%" + string + "%");
			preparedStatement.setLong(3, to - from);
			preparedStatement.setLong(4, from);
			resultSet = preparedStatement.executeQuery();
			computers = DaoUtil.getComputerList(resultSet);
		} catch (SQLException e) {
			throw new DaoException(DaoException.CAN_NOT_GET_ELEMENT, e);
		} finally {
			DaoUtil.close(preparedStatement, connection);
		}
		return computers;
	}

	@Override
	public List<Computer> getByCompanyId(Connection connection, Long id) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Computer> computers = null;
		try {
			preparedStatement = connection
					.prepareStatement("select * from computer compu LEFT JOIN company compa ON compu.company_id = compa.id WHERE compa.id = ?");
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

	@Override
	public Long getNameContainsElement(String string) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		Long result = null;
		try {
			connection = ConnectionFactory.INSTANCE.createConnection();
			preparedStatement = connection
					.prepareStatement("SELECT count(*) FROM computer compu LEFT JOIN company compa ON compu.company_id = compa.id WHERE compu.name LIKE ? or compa.name LIKE ?");
			preparedStatement.setString(1, "%" + string + "%");
			preparedStatement.setString(2, "%" + string + "%");
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			result = Long.valueOf(resultSet.getString("count(*)"));
		} catch (SQLException e) {
			throw new DaoException(DaoException.CAN_NOT_GET_ELEMENT, e);
		} finally {
			DaoUtil.close(preparedStatement, connection);
		}
		return result;
	}

}
