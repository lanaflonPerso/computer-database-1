package com.excilys.computerdatabase.service.services;

import java.util.List;

import com.excilys.computerdatabase.model.Rule;
import com.excilys.computerdatabase.model.UserDetail;

public interface SecurityService {
	public UserDetail getByName(String userName);
	public void create(UserDetail userDetail);
	public void updateRight(Rule rule);
	public void resetPassword(UserDetail userDetail);
	public void delete(String userName);
	public List<UserDetail> getAll();
}
