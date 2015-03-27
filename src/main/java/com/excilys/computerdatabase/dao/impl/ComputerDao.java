/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.computerdatabase.dao.ComputerDaoInterface;
import com.excilys.computerdatabase.exception.DaoException;
import com.excilys.computerdatabase.factory.ConnectionFactory;
import com.excilys.computerdatabase.mapper.ComputerMapper;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.sort.SortCriteria;
import com.excilys.computerdatabase.util.DaoUtil;

@Repository
public class ComputerDao implements ComputerDaoInterface {
	@Autowired
	ConnectionFactory connectionFactory;
	@Autowired
	ComputerMapper computerMapper;

	public ComputerDao() {
		super();
	}

	@Override
	public void create(Computer t) {
		Connection connection = connectionFactory.getConnection();
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
			DaoUtil.close(preparedStatement, connection);
		}
	}

	@Override
	public void delete(Long computerId) {
		Connection connection = connectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection
					.prepareStatement("DELETE FROM computer WHERE id=?");
			preparedStatement.setLong(1, computerId);
			preparedStatement.execute();
		} catch (SQLException e) {
			throw new DaoException(DaoException.CAN_NOT_DELETE_ELEMENT, e);
		} finally {
			DaoUtil.close(preparedStatement, connection);
		}
	}

	@Override
	public void update(Computer t) {
		PreparedStatement preparedStatement = null;
		Connection connection = connectionFactory.getConnection();
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
			DaoUtil.close(preparedStatement, connection);
		}
	}

	@Override
	public Computer getById(Long index) {
		Connection connection = connectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Computer computer = null;
		try {
			preparedStatement = connection
					.prepareStatement("select * from computer compu LEFT JOIN company compa ON compu.company_id = compa.id WHERE compu.id=?");
			preparedStatement.setLong(1, index);
			resultSet = preparedStatement.executeQuery();
			List<Computer> computers = computerMapper
					.mapListResultSetToModel(resultSet);
			computer = computers.get(0);
		} catch (Exception e) {
			throw new DaoException(DaoException.CAN_NOT_GET_ELEMENT, e);
		} finally {
			DaoUtil.close(preparedStatement, connection);
		}
		return computer;
	}

	@Override
	public List<Computer> getAll(SortCriteria sortCriteria) {
		Connection connection = connectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Computer> computers = null;
		try {

			StringBuilder stringBuilder = new StringBuilder(
					"select * from computer compu LEFT JOIN company compa ON compu.company_id = compa.id ORDER BY ");
			stringBuilder.append(sortCriteria.toString());
			preparedStatement = connection.prepareStatement(stringBuilder
					.toString());
			resultSet = preparedStatement.executeQuery();
			computers = computerMapper.mapListResultSetToModel(resultSet);
		} catch (SQLException e) {
			throw new DaoException(DaoException.CAN_NOT_GET_ELEMENT, e);
		} finally {
			DaoUtil.close(preparedStatement, connection);
		}
		return computers;
	}

	@Override
	public List<Computer> getAll(Long from, Long to, SortCriteria sortCriteria) {
		Connection connection = connectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Computer> computers = null;
		try {
			StringBuilder stringBuilder = new StringBuilder(
					"select * from computer compu LEFT JOIN company compa ON compu.company_id = compa.id ORDER BY ");
			stringBuilder.append(sortCriteria.toString());
			stringBuilder.append(" LIMIT ? OFFSET ? ");
			preparedStatement = connection.prepareStatement(stringBuilder
					.toString());
			preparedStatement.setLong(1, to - from);
			preparedStatement.setLong(2, from);
			resultSet = preparedStatement.executeQuery();
			computers = computerMapper.mapListResultSetToModel(resultSet);
		} catch (SQLException e) {
			throw new DaoException(DaoException.CAN_NOT_GET_ELEMENT, e);
		} finally {
			DaoUtil.close(preparedStatement, connection);
		}
		return computers;
	}

	@Override
	public Long getNumberOfElement() {
		Connection connection = connectionFactory.getConnection();
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
			DaoUtil.close(statement, connection);
		}
		return result;
	}

	@Override
	public List<Computer> getByName(String string, Long from, Long to,
			SortCriteria sortCriteria) {
		Connection connection = connectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Computer> computers = null;
		try {
			StringBuilder stringBuilder = new StringBuilder(
					"SELECT * FROM computer compu LEFT JOIN company compa ON compu.company_id = compa.id WHERE compu.name LIKE ? or compa.name LIKE ? ORDER BY ");
			stringBuilder.append(sortCriteria.toString());
			stringBuilder.append(" LIMIT ? OFFSET ? ");
			preparedStatement = connection.prepareStatement(stringBuilder
					.toString());
			preparedStatement.setString(1, "%" + string + "%");
			preparedStatement.setString(2, "%" + string + "%");
			preparedStatement.setLong(3, to - from);
			preparedStatement.setLong(4, from);
			resultSet = preparedStatement.executeQuery();
			computers = computerMapper.mapListResultSetToModel(resultSet);
		} catch (SQLException e) {
			throw new DaoException(DaoException.CAN_NOT_GET_ELEMENT, e);
		} finally {
			DaoUtil.close(preparedStatement, connection);
		}
		return computers;
	}

	@Override
	public List<Computer> getByCompanyId(Long id) {
		Connection connection = connectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Computer> computers = null;
		try {
			preparedStatement = connection
					.prepareStatement("select * from computer compu LEFT JOIN company compa ON compu.company_id = compa.id WHERE compa.id = ?");
			preparedStatement.setLong(1, id);
			resultSet = preparedStatement.executeQuery();
			computers = computerMapper.mapListResultSetToModel(resultSet);
		} catch (SQLException e) {
			throw new DaoException(DaoException.CAN_NOT_GET_ELEMENT, e);
		} finally {
			DaoUtil.close(preparedStatement, connection);
		}
		return computers;
	}

	@Override
	public Long getByNameNumberOfElement(String string) {
		Connection connection = connectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Long result = null;
		try {
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
