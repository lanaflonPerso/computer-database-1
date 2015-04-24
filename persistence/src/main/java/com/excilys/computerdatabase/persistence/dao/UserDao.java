package com.excilys.computerdatabase.persistence.dao;

import java.util.List;

import com.excilys.computerdatabase.persistence.model.User;

public interface UserDao {
	public void create(User user);
	public List<User> getAll();
	public User getByName(String userName);
	public void update(User user);
	public void delete(String userName);
}
