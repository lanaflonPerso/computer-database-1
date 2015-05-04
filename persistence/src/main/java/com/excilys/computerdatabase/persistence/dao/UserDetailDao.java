package com.excilys.computerdatabase.persistence.dao;

import java.util.List;

import com.excilys.computerdatabase.model.UserDetail;

/**
 * The Interface UserDetailDao.
 */
public interface UserDetailDao {
	
	/**
	 * Creates the.
	 *
	 * @param userDetail the user detail
	 */
	public void create(UserDetail userDetail);
	
	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	public List<UserDetail> getAll();
	
	/**
	 * Gets the by username.
	 *
	 * @param username the username
	 * @return the by username
	 */
	public UserDetail getByUsername(String username);
	
	/**
	 * Delete.
	 *
	 * @param username the username
	 */
	public void delete(String username);
	
	/**
	 * Update.
	 *
	 * @param userDetail the user detail
	 */
	public void update(UserDetail userDetail);
}
