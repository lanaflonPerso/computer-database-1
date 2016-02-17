package com.excilys.computerdatabase.service.services;

import com.excilys.computerdatabase.model.Rule;
import com.excilys.computerdatabase.model.UserDetail;

import java.util.List;

/**
 * @author Vincent Galloy
 *         The Interface SecurityService.
 */
public interface SecurityService {

    /**
     * Gets the by name.
     *
     * @param userName the user name
     * @return the by name
     */
    UserDetail getByName(String userName);

    /**
     * Creates the.
     *
     * @param userDetail the user detail
     */
    void create(UserDetail userDetail);

    /**
     * Update right.
     *
     * @param rule the rule
     */
    void updateRight(Rule rule);

    /**
     * Reset password.
     *
     * @param userDetail the user detail
     */
    void resetPassword(UserDetail userDetail);

    /**
     * Delete.
     *
     * @param userName the user name
     */
    void delete(String userName);

    /**
     * Gets the all.
     *
     * @return the all
     */
    List<UserDetail> getAll();
}
