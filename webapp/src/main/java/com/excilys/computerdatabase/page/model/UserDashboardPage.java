/**
 * @author Vincent Galloy
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
	
	/** The Error message. */
	private String ErrorMessage = null;

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
	 * @param errorMessage the error message
	 */
	public UserDashboardPage(List<UserDetailDto> userList, String errorMessage) {
		super();
		this.userList = userList;
		ErrorMessage = errorMessage;
	}

	public List<UserDetailDto> getUserList() {
		return userList;
	}

	public void setUserList(List<UserDetailDto> userList) {
		this.userList = userList;
	}

	public String getErrorMessage() {
		return ErrorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		ErrorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "UserDashboardPage [userList=" + userList + ", ErrorMessage=" + ErrorMessage + "]";
	}

}