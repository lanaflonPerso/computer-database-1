package com.excilys.computerdatabase.service.validation;

import com.excilys.computerdatabase.model.Role;
import com.excilys.computerdatabase.model.Rule;
import com.excilys.computerdatabase.model.UserDetail;

public class ServiceValidator {
	public static final String INVALID_USER = "invalid user";
	public static final String INVALID_USERNAME = "invalid username";
	public static final String INVALID_RULE = "invalid rule";

	public static boolean isUserNameCorrect(String userName) {
		if (userName == null) {
			return false;
		} else if (userName.trim().isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean isUserCorrect(UserDetail userDetail) {
		if (userDetail == null) {
			return false;
		} else if (userDetail.getUserName() == null) {
			return false;
		} else if (userDetail.getUserName().trim().isEmpty()) {
			return false;
		} else if (userDetail.getPassword() == null) {
			return false;
		} else if (userDetail.getPassword().trim().isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean isRuleCorrect(Rule rule) {
		if (rule == null) {
			return false;
		} else if (!isUserNameCorrect(rule.getUserName())) {
			return false;
		} else if (rule.getRole() == null || Role.NONE.equals(rule.getRole())) {
			return false;
		} else {
			return true;
		}
	}
}
