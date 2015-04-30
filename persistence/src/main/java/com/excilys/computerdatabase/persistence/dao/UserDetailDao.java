package com.excilys.computerdatabase.persistence.dao;

import java.util.List;

import com.excilys.computerdatabase.model.UserDetail;

public interface UserDetailDao {
	public void create(UserDetail userDetail);
	public List<UserDetail> getAll();
	public UserDetail getByUsername(String username);
	public void delete(String username);
	public void update(UserDetail userDetail);
}
