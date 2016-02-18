package com.excilys.computerdatabase.page.model;

import com.excilys.computerdatabase.dto.model.UserDetailDto;

import java.util.List;

/**
 * @author Vincent Galloy
 *         The Class UserDashboardPage.
 */
public class UserDashboardPage {
    private List<UserDetailDto> userList;
    private String errorMessage;

    /**
     * Instantiates a new user dashboard page.
     */
    public UserDashboardPage() {
        super();
    }

    public List<UserDetailDto> getUserList() {
        return userList;
    }

    public void setUserList(List<UserDetailDto> userList) {
        this.userList = userList;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "UserDashboardPage [userList=" + userList + ", errorMessage=" + errorMessage + "]";
    }

}