package com.excilys.computerdatabase.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Vincent Galloy
 * The Class User.
 */
@Entity
@Table(name = "users")
public class User implements Serializable {
    private static final long serialVersionUID = -1574644808526138085L;
    @Id
    @Column(name = "username", unique = true, nullable = false, length = 100)
    private String username;
    @Column(name = "password", nullable = false, length = 100)
    private String password;
    @Column(name = "enabled", nullable = false)
    private boolean enabled;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<UserRole> userRole = new HashSet<>(0);

    /**
     * Instantiates a new user.
     */
    public User() {
        super();
    }

    /**
     * Instantiates a new user.
     *
     * @param username the username
     * @param password the password
     * @param enabled  the enabled
     */
    public User(String username, String password, boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    /**
     * Instantiates a new user.
     *
     * @param username the username
     * @param password the password
     * @param enabled  the enabled
     * @param userRole the user role
     */
    public User(String username, String password, boolean enabled, Set<UserRole> userRole) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.userRole = userRole;
    }

    /**
     * Gets the username.
     *
     * @return the username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Sets the username.
     *
     * @param username the new username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Sets the password.
     *
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Checks if is enabled.
     *
     * @return true, if is enabled
     */
    public boolean isEnabled() {
        return this.enabled;
    }

    /**
     * Sets the enabled.
     *
     * @param enabled the new enabled
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Gets the user role.
     *
     * @return the user role
     */
    public Set<UserRole> getUserRole() {
        return this.userRole;
    }

    /**
     * Sets the user role.
     *
     * @param userRole the new user role
     */
    public void setUserRole(Set<UserRole> userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "User [username=" + username + ", password=" + password + ", enabled=" + enabled + ", userRole=" + userRole + "]";
    }
}
