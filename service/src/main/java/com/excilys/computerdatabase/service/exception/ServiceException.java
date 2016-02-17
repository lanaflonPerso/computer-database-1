package com.excilys.computerdatabase.service.exception;

/**
 * @author Vincent Galloy
 *         The Class ServiceException.
 */
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 4038430361708027609L;

    /**
     * Instantiates a new service exception.
     *
     * @param message the message
     */
    public ServiceException(String message) {
        super(message);
    }
}
