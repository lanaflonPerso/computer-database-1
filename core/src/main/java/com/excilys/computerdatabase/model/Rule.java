package com.excilys.computerdatabase.model;

/**
 * @author Vincent Galloy
 * The Class Rule.
 */
public class Rule {
    private String username;
    private Role role;
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
     * @param username   the user name
     * @param role       the role
     * @param authorized the authorized
     */
    public Rule(String username, Role role, boolean authorized) {
        this.username = username;
        this.role = role;
        this.authorized = authorized;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
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
        result = prime * result + ((username == null) ? 0 : username.hashCode());
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
        Rule other = (Rule) obj;
        if (authorized != other.authorized)
            return false;
        if (role != other.role)
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Rule [username=" + username + ", role=" + role + ", authorized=" + authorized + "]";
    }
}
