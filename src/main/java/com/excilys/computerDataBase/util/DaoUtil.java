package com.excilys.computerDataBase.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerDataBase.exception.DaoException;
import com.excilys.computerDataBase.mapper.CompanyMapper;
import com.excilys.computerDataBase.mapper.ComputerMapper;
import com.excilys.computerDataBase.model.Company;
import com.excilys.computerDataBase.model.Computer;

public class DaoUtil {
	public static List<Computer> getComputerList(ResultSet resultSet)
			throws SQLException {
		List<Computer> computers = new ArrayList<Computer>();
		while (resultSet.next()) {
			Computer computer = ComputerMapper.mapResultSetToModel(resultSet);
			computers.add(computer);
		}
		return computers;
	}

	public static List<Company> getCompanyList(ResultSet resultSet)
			throws SQLException {
		List<Company> companies = new ArrayList<Company>();
		while (resultSet.next()) {
			Company company = CompanyMapper.mapResultSetToModel(resultSet);
			companies.add(company);
		}
		return companies;
	}
	
	public static void close(Statement statement, Connection connection) {
		closeStatement(statement);
		closeConnection(connection);
	}
	
	public static void closeStatement(Statement statement) {
		try {
			if (statement != null) {
				statement.close();
			}
		} catch (SQLException e) {
			throw new DaoException(DaoException.CAN_NOT_CLOSE_STATEMENT, e);
		}
	}

	public static void closeConnection(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			throw new DaoException(DaoException.CAN_NOT_CLOSE_CONNECTION, e);
		}
	}
}
