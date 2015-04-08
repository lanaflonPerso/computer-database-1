/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.excilys.computerdatabase.dao.CompanyDao;
import com.excilys.computerdatabase.exception.DaoException;
import com.excilys.computerdatabase.mapper.CompanyMapper;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.sort.SortCriteria;
import com.excilys.computerdatabase.validation.Validator;

@Repository
public class CompanyDaoImpl implements CompanyDao {
	@Autowired
	DataSource dataSource;
	@Autowired
	CompanyMapper companyMapper;

	@Override
	public void create(Company t) {
		if (!Validator.isCompanyCorrect(t)) {
			throw new DaoException(Validator.INVALID_COMPANY);
		}
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		KeyHolder holder = new GeneratedKeyHolder();
		PreparedStatementCreator preparedStatement = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection
						.prepareStatement(
								"INSERT INTO company (name) VALUES (?)",
								Statement.RETURN_GENERATED_KEYS);
				if (t.getName().trim().isEmpty()) {
					throw new NullPointerException();
				}
				preparedStatement.setString(1, t.getName().trim());
				return preparedStatement;
			}
		};

		jdbcTemplate.update(preparedStatement, holder);
		Long id = holder.getKey().longValue();
		t.setId(id);

	}

	@Override
	public List<Company> getAll(SortCriteria sortCriteria) {
		if (!Validator.isSortCriteriaCorrect(sortCriteria)) {
			throw new DaoException(Validator.INVALID_SORT_CRITERIA);
		}
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		StringBuilder stringBuilder = new StringBuilder().append(
				"select * FROM company ORDER BY ").append(
				sortCriteria.toString());

		return jdbcTemplate.query(stringBuilder.toString(), companyMapper);
	}

	@Override
	public List<Company> getAll(Long from, Long to, SortCriteria sortCriteria) {
		if (!Validator.isDateFromToCorrect(from, to)) {
			throw new DaoException(Validator.INVALID_BOUND);
		}
		if (!Validator.isSortCriteriaCorrect(sortCriteria)) {
			throw new DaoException(Validator.INVALID_SORT_CRITERIA);
		}
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		StringBuilder stringBuilder = new StringBuilder()
				.append("select * FROM company ORDER BY ")
				.append(sortCriteria.toString()).append(" LIMIT ")
				.append(new Long(to - from).toString()).append(" OFFSET ")
				.append(from.toString());

		return jdbcTemplate.query(stringBuilder.toString(), companyMapper);
	}

	@Override
	public Long getNumberOfElement() {
		return new JdbcTemplate(dataSource).queryForObject(
				"select count(*) from company", Long.class);
	}

	@Override
	public void delete(Long id) {
		if (!Validator.isIdCorrect(id)) {
			throw new DaoException(Validator.INVALID_COMPANY_ID);
		}
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		StringBuilder stringBuilder = new StringBuilder().append(
				"DELETE FROM company WHERE id=").append(id);
		jdbcTemplate.execute(stringBuilder.toString());
	}

	@Override
	public Company getById(Long id) {
		if (!Validator.isIdCorrect(id)) {
			throw new DaoException(Validator.INVALID_COMPANY_ID);
		}
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		StringBuilder stringBuilder = new StringBuilder().append(
				"select * from company WHERE id=").append(id);

		List<Company> companies = jdbcTemplate.query(stringBuilder.toString(),
				companyMapper);
		if (companies.size() == 0) {
			throw new DaoException(DaoException.CAN_NOT_GET_ELEMENT);
		}
		return companies.get(0);
	}

}
