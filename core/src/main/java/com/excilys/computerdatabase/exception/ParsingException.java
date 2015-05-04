/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.exception;

/**
 * The Class ParsingException.
 *
 * @author excilys
 */
public class ParsingException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	/** The Constant CAN_NOT_PARSE_INTO_LONG. */
	public static final String CAN_NOT_PARSE_INTO_LONG = "can not parse into Long";
	
	/** The Constant CAN_NOT_MAP_RESULT_SET. */
	public static final String CAN_NOT_MAP_RESULT_SET = "can not map result set";

	/**
	 * Instantiates a new parsing exception.
	 */
	public ParsingException() {
		super();
	}

	/**
	 * Instantiates a new parsing exception.
	 *
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 * @param enableSuppression
	 *            the enable suppression
	 * @param writableStackTrace
	 *            the writable stack trace
	 */
	public ParsingException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * Instantiates a new parsing exception.
	 *
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public ParsingException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new parsing exception.
	 *
	 * @param message
	 *            the message
	 */
	public ParsingException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new parsing exception.
	 *
	 * @param cause
	 *            the cause
	 */
	public ParsingException(Throwable cause) {
		super(cause);
	}

}
