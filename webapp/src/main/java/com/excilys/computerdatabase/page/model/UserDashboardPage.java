/**
 * @Author Vincent Galloy
 * 
 */
package com.excilys.computerdatabase.page.model;

import java.util.List;

import com.excilys.computerdatabase.dto.model.UserDetailDto;

/**
 * The Class UserDashboardPage.
 */
public class UserDashboardPage {
	
	/** The user list. */
	private List<UserDetailDto> userList;

	/**
	 * Instantiates a new user dashboard page.
	 */
	public UserDashboardPage() {
		super();
	}

	/**
	 * Instantiates a new user dashboard page.
	 *
	 * @param userList the user list
	 */
	public UserDashboardPage(List<UserDetailDto> userList) {
		super();
		this.userList = userList;
	}

	/**
	 * Gets the user list.
	 *
	 * @return the user list
	 */
	public List<UserDetailDto> getUserList() {
		return userList;
	}

	/**
	 * Sets the user list.
	 *
	 * @param userList the new user list
	 */
	public void setUserList(List<UserDetailDto> userList) {
		this.userList = userList;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserDashboardPage [userList=" + userList + "]";
	}
	
}
