/**
 * @Author Vincent Galloy
 * 
 */
package com.excilys.computerdatabase.dto.model;

import java.util.List;

/**
 * The Class UserDetailDto.
 */
public class UserDetailDto {

	/** The user name. */
	private String userName;

	/** The password. */
	private String password;

	/** The roles. */
	private List<String> roles;

	/**
	 * Instantiates a new user detail dto.
	 */
	public UserDetailDto() {
		super();
	}

	/**
	 * Instantiates a new user detail dto.
	 *
	 * @param userName
	 *          the user name
	 * @param roles
	 *          the roles
	 */
	public UserDetailDto(String userName, List<String> roles) {
		super();
		this.userName = userName;
		this.roles = roles;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "UserDetailDto [userName=" + userName + ", password=" + password + ", roles=" + roles + "]";
	}

}
