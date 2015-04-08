/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.excilys.computerdatabase.dao.ComputerDao;
import com.excilys.computerdatabase.exception.DaoException;
import com.excilys.computerdatabase.mapper.ComputerMapper;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.sort.SortCriteria;
import com.excilys.computerdatabase.validation.Validator;

@Repository
public class ComputerDaoImpl implements ComputerDao {
	@Autowired
	ComputerMapper computerMapper;
	@Autowired
	DataSource dataSource;

	@Override
	public void create(Computer t) {
		if (!Validator.isComputerCorrect(t)) {
			throw new DaoException(Validator.INVALID_COMPUTER);
		}

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		KeyHolder holder = new GeneratedKeyHolder();
		PreparedStatementCreator preparedStatement = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection
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
				return preparedStatement;
			}
		};

		jdbcTemplate.update(preparedStatement, holder);
		Long id = holder.getKey().longValue();
		t.setId(id);
	}

	@Override
	public void delete(Long id) {
		if (!Validator.isIdCorrect(id)) {
			throw new DaoException(Validator.INVALID_COMPUTER_ID);
		}
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		StringBuilder stringBuilder = new StringBuilder().append(
				"DELETE FROM computer WHERE id=").append(id);
		jdbcTemplate.execute(stringBuilder.toString());
	}

	@Override
	public void update(Computer t) {
		if (!Validator.isComputerCorrect(t)) {
			throw new DaoException(Validator.INVALID_COMPUTER);
		}

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		PreparedStatementCreator preparedStatement = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection
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
				return preparedStatement;
			}
		};

		jdbcTemplate.update(preparedStatement);
	}

	@Override
	public Computer getById(Long id) {
		if (!Validator.isIdCorrect(id)) {
			throw new DaoException(Validator.INVALID_COMPANY_ID);
		}

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		StringBuilder stringBuilder = new StringBuilder()
				.append("select * from computer compu LEFT JOIN company compa ON compu.company_id = compa.id WHERE compu.id=")
				.append(id);

		List<Computer> computers = jdbcTemplate.query(stringBuilder.toString(),
				computerMapper);
		if (computers.size() == 0) {
			throw new DaoException(DaoException.CAN_NOT_GET_ELEMENT);
		}
		return computers.get(0);
	}

	@Override
	public List<Computer> getAll(SortCriteria sortCriteria) {
		if (!Validator.isSortCriteriaCorrect(sortCriteria)) {
			throw new DaoException(Validator.INVALID_SORT_CRITERIA);
		}

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		StringBuilder stringBuilder = new StringBuilder()
				.append("select * from computer compu LEFT JOIN company compa ON compu.company_id = compa.id ORDER BY ")
				.append(sortCriteria.toString());

		return jdbcTemplate.query(stringBuilder.toString(), computerMapper);
	}

	@Override
	public List<Computer> getAll(Long from, Long to, SortCriteria sortCriteria) {
		if (!Validator.isDateFromToCorrect(from, to)) {
			throw new DaoException(Validator.INVALID_COMPUTER);
		}
		if (!Validator.isSortCriteriaCorrect(sortCriteria)) {
			throw new DaoException(Validator.INVALID_SORT_CRITERIA);
		}

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		StringBuilder stringBuilder = new StringBuilder()
				.append("select * from computer compu LEFT JOIN company compa ON compu.company_id = compa.id ORDER BY ")
				.append(sortCriteria.toString()).append(" LIMIT ")
				.append(new Long(to - from).toString()).append(" OFFSET ")
				.append(from.toString());

		return jdbcTemplate.query(stringBuilder.toString(), computerMapper);
	}

	@Override
	public Long getNumberOfElement() {
		return new JdbcTemplate(dataSource).queryForObject(
				"select count(*) from computer", Long.class);
	}

	@Override
	public List<Computer> getByName(String string, Long from, Long to,
			SortCriteria sortCriteria) {
		if (!Validator.isStringForSearchCorrect(string)) {
			throw new DaoException(Validator.INVALID_STRING_FOR_SEARCH);
		}
		if (!Validator.isDateFromToCorrect(from, to)) {
			throw new DaoException(Validator.INVALID_BOUND);
		}
		if (!Validator.isSortCriteriaCorrect(sortCriteria)) {
			throw new DaoException(Validator.INVALID_SORT_CRITERIA);
		}

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		StringBuilder stringBuilder = new StringBuilder()
				.append("SELECT * FROM computer compu LEFT JOIN company compa ON compu.company_id = compa.id WHERE compu.name LIKE ? or compa.name LIKE ? ORDER BY ")
				.append(sortCriteria.toString()).append(" LIMIT ? OFFSET ?");
		StringBuilder likeStringBuilder = new StringBuilder().append("%").append(string).append("%");
		
		return jdbcTemplate.query(stringBuilder.toString(), computerMapper, new Object [] {likeStringBuilder.toString(), likeStringBuilder.toString(), to - from, from});
	}

	@Override
	public List<Computer> getByCompanyId(Long id) {
		if (!Validator.isIdCorrect(id)) {
			throw new DaoException(Validator.INVALID_COMPANY_ID);
		}

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		StringBuilder stringBuilder = new StringBuilder()
				.append("select * from computer compu LEFT JOIN company compa ON compu.company_id = compa.id WHERE compa.id = ")
				.append(id);

		return jdbcTemplate.query(stringBuilder.toString(), computerMapper);

	}

	@Override
	public Long getByNameNumberOfElement(String string) {
		if (!Validator.isStringForSearchCorrect(string)) {
			throw new DaoException(Validator.INVALID_STRING_FOR_SEARCH);
		}

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		StringBuilder stringBuilder = new StringBuilder()
				.append("SELECT count(*) FROM computer compu LEFT JOIN company compa ON compu.company_id = compa.id WHERE compu.name LIKE ? or compa.name LIKE ?");
		StringBuilder likeStringBuilder = new StringBuilder().append("%").append(string).append("%");
		return jdbcTemplate.queryForObject(stringBuilder.toString(), Long.class, new Object[] {likeStringBuilder.toString(), likeStringBuilder.toString()});
	}

}
