package com.excilys.computerdatabase.model;

/**
 * The Class Rule.
 */
public class Rule {
	
	/** The user name. */
	private String userName;
	
	/** The role. */
	private Role role;
	
	/** The authorized. */
	private boolean authorized;

	/**
	 * Instantiates a new rule.
	 */
	public Rule() {
		super();
	}

	/**
	 * Instantiates a new rule.
	 *
	 * @param userName the user name
	 * @param role the role
	 * @param authorized the authorized
	 */
	public Rule(String userName, Role role, boolean authorized) {
		super();
		this.userName = userName;
		this.role = role;
		this.authorized = authorized;
	}

	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the user name.
	 *
	 * @param userName the new user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Gets the role.
	 *
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * Sets the role.
	 *
	 * @param role the new role
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * Checks if is authorized.
	 *
	 * @return true, if is authorized
	 */
	public boolean isAuthorized() {
		return authorized;
	}

	/**
	 * Sets the authorized.
	 *
	 * @param authorized the new authorized
	 */
	public void setAuthorized(boolean authorized) {
		this.authorized = authorized;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (authorized ? 1231 : 1237);
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rule other = (Rule) obj;
		if (authorized != other.authorized)
			return false;
		if (role != other.role)
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Rule [userName=" + userName + ", role=" + role + ", authorized=" + authorized + "]";
	}

}
