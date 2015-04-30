package com.excilys.computerdatabase.model;

public enum Role {
	NONE, USER, ADMIN, SUPER_ADMIN;

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
