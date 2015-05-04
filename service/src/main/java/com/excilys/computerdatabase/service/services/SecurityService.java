/**
 * @Author Vincent Galloy
 * 
 */
package com.excilys.computerdatabase.service.services;

import java.util.List;

import com.excilys.computerdatabase.model.Rule;
import com.excilys.computerdatabase.model.UserDetail;

/**
 * The Interface SecurityService.
 */
public interface SecurityService {
	
	/**
	 * Gets the by name.
	 *
	 * @param userName the user name
	 * @return the by name
	 */
	public UserDetail getByName(String userName);
	
	/**
	 * Creates the.
	 *
	 * @param userDetail the user detail
	 */
	public void create(UserDetail userDetail);
	
	/**
	 * Update right.
	 *
	 * @param rule the rule
	 */
	public void updateRight(Rule rule);
	
	/**
	 * Reset password.
	 *
	 * @param userDetail the user detail
	 */
	public void resetPassword(UserDetail userDetail);
	
	/**
	 * Delete.
	 *
	 * @param userName the user name
	 */
	public void delete(String userName);
	
	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	public List<UserDetail> getAll();
}
