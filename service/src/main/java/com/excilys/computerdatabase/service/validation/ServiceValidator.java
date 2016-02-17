package com.excilys.computerdatabase.service.validation;

import com.excilys.computerdatabase.model.Role;
import com.excilys.computerdatabase.model.Rule;
import com.excilys.computerdatabase.model.UserDetail;

/**
 * @author Vincent Galloy
 *         The Class ServiceValidator.
 */
public class ServiceValidator {
    public static final String INVALID_USER = "invalid user";
    public static final String INVALID_USERNAME = "invalid username";
    public static final String INVALID_RULE = "invalid rule";

    /**
     * Checks if is user name correct.
     *
     * @param userName the user name
     * @return true, if is user name correct
     */
    public static boolean isUserNameCorrect(String userName) {
        return userName != null && !userName.trim().isEmpty();
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
        } else return !userDetail.getPassword().trim().isEmpty();
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
