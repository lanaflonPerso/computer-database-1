package com.excilys.computerdatabase.console.exception;

/**
 * The Class ConsoleException.
 */
public class ConsoleException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4752359502580121210L;

	/**
	 * Instantiates a new console exception.
	 */
	public ConsoleException() {
		super();
	}

	/**
	 * Instantiates a new console exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 * @param enableSuppression the enable suppression
	 * @param writableStackTrace the writable stack trace
	 */
	public ConsoleException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * Instantiates a new console exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public ConsoleException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new console exception.
	 *
	 * @param message the message
	 */
	public ConsoleException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new console exception.
	 *
	 * @param cause the cause
	 */
	public ConsoleException(Throwable cause) {
		super(cause);
	}

}
