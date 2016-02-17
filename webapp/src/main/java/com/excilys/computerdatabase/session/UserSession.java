package com.excilys.computerdatabase.session;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author Vincent Galloy
 *         The Class UserSession.
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserSession implements Serializable {
    private static final long serialVersionUID = 6908089200060464909L;
    private String errorMessage;

    /**
     * Instantiates a new user session.
     */
    public UserSession() {
        super();
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
