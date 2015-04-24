package com.excilys.computerdatabase.persistence.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.excilys.computerdatabase.persistence.dao.AuthorityDao;
import com.excilys.computerdatabase.persistence.model.Authority;

@Repository
public class AuthorityDaoImpl implements AuthorityDao {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private static final String CREATE_AUTHORITY = "INSERT INTO authorities (username, authority) VALUES (?, ?)";
	private static final String GET_BY_USERNAME = "SELECT authority FROM authorities WHERE username=?";
	private static final String DELETE_AUTHORITY = "DELETE FROM authorities WHERE username=? and authority=?";
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public AuthorityDaoImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void create(Authority authority) {
		log.trace("create : {} ", authority);
		jdbcTemplate.update(CREATE_AUTHORITY, new Object[] { authority.getUserName(), authority.getRole() });
	}

	@Override
	public List<Authority> getByName(String userName) {
		log.trace("getByName : {} ", userName);
		return jdbcTemplate.query(GET_BY_USERNAME, new String[] { userName }, new RowMapper<Authority>() {
			public Authority mapRow(ResultSet rs, int rowNum) throws SQLException {
				String roleName = rs.getString("authority");
				return new Authority(userName, roleName);
			}
		});
	}

	@Override
	public void delete(Authority authority) {
		log.trace("delete : {} ", authority);
		jdbcTemplate.update(DELETE_AUTHORITY, new Object[] { authority.getUserName(), authority.getRole() });
	}
}
