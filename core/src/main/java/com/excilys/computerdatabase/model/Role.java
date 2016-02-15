package com.excilys.computerdatabase.model;

/**
 * The Enum Role.
 */
public enum Role {
	
	/** The none. */
	NONE, 
 /** The user. */
 USER, 
 /** The admin. */
 ADMIN, 
 /** The super admin. */
 SUPER_ADMIN;

	@Override
	public String toString() {
		switch (this) {
		case USER:
			return "ROLE_USER";
		case ADMIN:
			return "ROLE_ADMIN";
		case SUPER_ADMIN:
			return "ROLE_SUPER_ADMIN";
		case NONE:
		default:
			return null;
		}
	}

	/**
	 * Builds the.
	 *
	 * @param role the role
	 * @return the role
	 */
	public static Role build(String role) {
		if(role == null) {
			return NONE;
		}
		switch (role) {
		case "ROLE_USER":
			return USER;
		case "ROLE_ADMIN":
			return ADMIN;
		case "ROLE_SUPER_ADMIN":
				return SUPER_ADMIN;
		default:
			return NONE;
		}
	}
	
	
}
