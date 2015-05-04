/**
 * @Author Vincent Galloy
 * 
 */
package com.excilys.computerdatabase.session;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * The Class UserSession.
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserSession implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6908089200060464909L;

	/** The error message. */
	private String errorMessage;

	/**
	 * Instantiates a new user session.
	 */
	public UserSession() {
		super();
	}

	/**
	 * Instantiates a new user session.
	 *
	 * @param errorMessage the error message
	 */
	public UserSession(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "UserSession [errorMessage=" + errorMessage + "]";
	}

}
