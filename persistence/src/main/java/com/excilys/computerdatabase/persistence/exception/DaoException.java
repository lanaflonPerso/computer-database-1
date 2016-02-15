package com.excilys.computerdatabase.persistence.exception;

/**
 * @author Vincent Galloy
 */
public class DaoException extends RuntimeException {
    private static final long serialVersionUID = -8675015884763156959L;

    /**
     * Instantiates a new data base exception.
     *
     * @param message the message
     */
    public DaoException(String message) {
        super(message);
    }
}
