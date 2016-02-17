package com.excilys.computerdatabase.persistence.dao;

import com.excilys.computerdatabase.model.UserDetail;

import java.util.List;

/**
 * @author Vincent Galloy
 *         The Interface UserDetailDao.
 */
public interface UserDetailDao {

    /**
     * Creates the.
     *
     * @param userDetail the user detail
     */
    void create(UserDetail userDetail);

    /**
     * Gets the all.
     *
     * @return the all
     */
    List<UserDetail> getAll();

    /**
     * Gets the by username.
     *
     * @param username the username
     * @return the by username
     */
    UserDetail getByUsername(String username);

    /**
     * Delete.
     *
     * @param username the username
     */
    void delete(String username);

    /**
     * Update.
     *
     * @param userDetail the user detail
     */
    void update(UserDetail userDetail);
}
