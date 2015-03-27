/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.computerdatabase.dao.CompanyDaoInterface;
import com.excilys.computerdatabase.exception.DaoException;
import com.excilys.computerdatabase.factory.ConnectionFactory;
import com.excilys.computerdatabase.mapper.CompanyMapper;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.sort.SortCriteria;
import com.excilys.computerdatabase.util.DaoUtil;

@Repository
public class CompanyDao implements CompanyDaoInterface {
	@Autowired
	ConnectionFactory connectionFactory;
	@Autowired
	CompanyMapper companyMapper;

	@Override
	public void create(Company t) {
		Connection connection = connectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(
					"INSERT INTO company (name) VALUES (?)",
					Statement.RETURN_GENERATED_KEYS);
			if (t.getName().trim().isEmpty()) {
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
		Connection connection = connectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Company> companies = null;
		try {
			connection = connectionFactory.getConnection();
			StringBuilder stringBuilder = new StringBuilder(
					"select * FROM company ORDER BY ");
			stringBuilder.append(sortCriteria.toString());
			preparedStatement = connection.prepareStatement(stringBuilder
					.toString());
			resultSet = preparedStatement.executeQuery();
			companies = companyMapper.mapListResultSetToModel(resultSet);
		} catch (SQLException e) {
			throw new DaoException(DaoException.CAN_NOT_GET_ELEMENT, e);
		} finally {
			DaoUtil.close(preparedStatement, connection);
		}
		return companies;
	}

	@Override
	public List<Company> getAll(Long from, Long to, SortCriteria sortCriteria) {
		Connection connection = connectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Company> companies = null;
		try {
			connection = connectionFactory.getConnection();
			StringBuilder stringBuilder = new StringBuilder(
					"select * FROM company ORDER BY ");
			stringBuilder.append(sortCriteria.toString());
			stringBuilder.append(" LIMIT ? OFFSET ?");
			preparedStatement = connection.prepareStatement(stringBuilder
					.toString());
			preparedStatement.setLong(1, to - from);
			preparedStatement.setLong(2, from);
			resultSet = preparedStatement.executeQuery();
			companies = companyMapper.mapListResultSetToModel(resultSet);
		} catch (SQLException e) {
			throw new DaoException(DaoException.CAN_NOT_GET_ELEMENT, e);
		} finally {
			DaoUtil.close(preparedStatement, connection);
		}
		return companies;
	}

	@Override
	public Long getNumberOfElement() {
		Connection connection = connectionFactory.getConnection();
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
			DaoUtil.close(statement, connection);
		}
		return result;
	}

	@Override
	public void delete(Long id) {
		Connection connection = connectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection
					.prepareStatement("DELETE FROM company WHERE id=?");
			preparedStatement.setLong(1, id);
			preparedStatement.execute();
		} catch (SQLException e) {
			throw new DaoException(DaoException.CAN_NOT_DELETE_ELEMENT, e);
		} finally {
			DaoUtil.close(preparedStatement, connection);
		}
	}

	@Override
	public Company getById(Long id) {
		Connection connection = connectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Company company = null;
		try {
			preparedStatement = connection
					.prepareStatement("select * from company WHERE id=?");
			preparedStatement.setLong(1, id);
			resultSet = preparedStatement.executeQuery();
			List<Company> companies = companyMapper
					.mapListResultSetToModel(resultSet);
			company = companies.get(0);
		} catch (Exception e) {
			throw new DaoException(DaoException.CAN_NOT_GET_ELEMENT, e);
		} finally {
			DaoUtil.close(preparedStatement, connection);
		}
		return company;
	}

}
