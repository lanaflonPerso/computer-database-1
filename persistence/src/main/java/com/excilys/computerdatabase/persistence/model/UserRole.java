package com.excilys.computerdatabase.persistence.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * The Class UserRole.
 */
@Entity
@Table(name = "authorities", uniqueConstraints = @UniqueConstraint(columnNames = { "role", "username" }))
public class UserRole {

	/** The user role id. */
	private Integer userRoleId;
	
	/** The user. */
	private User user;
	
	/** The role. */
	private String role;

	/**
	 * Instantiates a new user role.
	 */
	public UserRole() {
	}

	/**
	 * Instantiates a new user role.
	 *
	 * @param user the user
	 * @param role the role
	 */
	public UserRole(User user, String role) {
		this.user = user;
		this.role = role;
	}

	/**
	 * Gets the user role id.
	 *
	 * @return the user role id
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "user_role_id", unique = true, nullable = false)
	public Integer getUserRoleId() {
		return this.userRoleId;
	}

	/**
	 * Sets the user role id.
	 *
	 * @param userRoleId the new user role id
	 */
	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "username", nullable = false)
	public User getUser() {
		return this.user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Gets the role.
	 *
	 * @return the role
	 */
	@Column(name = "role", nullable = false, length = 100)
	public String getRole() {
		return this.role;
	}

	/**
	 * Sets the role.
	 *
	 * @param role the new role
	 */
	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserRole [userRoleId=" + userRoleId + ", role=" + role + "]";
	}

}
