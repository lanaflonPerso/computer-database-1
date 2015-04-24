package com.excilys.computerdatabase.service.dao;

import java.util.List;

import com.excilys.computerdatabase.model.UserDetail;

public interface UserDetailDao {
	public void create(UserDetail userDetail);
	public UserDetail getByName(String userName);
	public void updated(UserDetail userDetail);
	public void delete(String userName);
	public List<UserDetail> getAll();
	public void updatePassword(UserDetail userDetail);
}
