package com.excilys.computerdatabase.dto.model;

/**
 * @author Vincent Galloy
 *         The Class RuleDto.
 */
public class RuleDto {
    private String userName;
    private String role;
    private boolean authorized;

    /**
     * Instantiates a new rule dto.
     */
    public RuleDto() {
        super();
    }

    /**
     * Instantiates a new rule dto.
     *
     * @param userName   the user name
     * @param role       the role
     * @param authorized the authorized
     */
    public RuleDto(String userName, String role, boolean authorized) {
        super();
        this.userName = userName;
        this.role = role;
        this.authorized = authorized;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isAuthorized() {
        return authorized;
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (authorized ? 1231 : 1237);
        result = prime * result + ((role == null) ? 0 : role.hashCode());
        result = prime * result + ((userName == null) ? 0 : userName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RuleDto other = (RuleDto) obj;
        if (authorized != other.authorized)
            return false;
        if (role == null) {
            if (other.role != null)
                return false;
        } else if (!role.equals(other.role))
            return false;
        if (userName == null) {
            if (other.userName != null)
                return false;
        } else if (!userName.equals(other.userName))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Rule [userName=" + userName + ", role=" + role + ", authorized=" + authorized + "]";
    }
}
