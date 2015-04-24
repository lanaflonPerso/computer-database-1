package com.excilys.computerdatabase.persistence.dao;

import java.util.List;

import com.excilys.computerdatabase.persistence.model.Authority;

public interface AuthorityDao {
	public void create(Authority authority);
	public List<Authority> getByName(String userName);
	public void delete(Authority authority);
}
