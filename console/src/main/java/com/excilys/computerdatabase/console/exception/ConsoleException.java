package com.excilys.computerdatabase.console.exception;

/**
 * @author Vincent Galloy
 *         The Class ConsoleException.
 */
public class ConsoleException extends RuntimeException {
    private static final long serialVersionUID = -4752359502580121210L;

    /**
     * Instantiates a new console exception.
     *
     * @param message the message
     */
    public ConsoleException(String message) {
        super(message);
    }
}
