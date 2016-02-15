/**
 * @author Vincent Galloy
 * 
 */
package com.excilys.computerdatabase.service.validation;

import com.excilys.computerdatabase.model.Role;
import com.excilys.computerdatabase.model.Rule;
import com.excilys.computerdatabase.model.UserDetail;

/**
 * The Class ServiceValidator.
 */
public class ServiceValidator {
	
	/** The Constant INVALID_USER. */
	public static final String INVALID_USER = "invalid user";
	
	/** The Constant INVALID_USERNAME. */
	public static final String INVALID_USERNAME = "invalid username";
	
	/** The Constant INVALID_RULE. */
	public static final String INVALID_RULE = "invalid rule";

	/**
	 * Checks if is user name correct.
	 *
	 * @param userName the user name
	 * @return true, if is user name correct
	 */
	public static boolean isUserNameCorrect(String userName) {
		if (userName == null) {
			return false;
		} else if (userName.trim().isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Checks if is user correct.
	 *
	 * @param userDetail the user detail
	 * @return true, if is user correct
	 */
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

	/**
	 * Checks if is rule correct.
	 *
	 * @param rule the rule
	 * @return true, if is rule correct
	 */
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
