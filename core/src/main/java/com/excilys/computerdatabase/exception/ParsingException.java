package com.excilys.computerdatabase.exception;

/**
 * The Class ParsingException.
 *
 * @author Vincent Galloy
 */
public class ParsingException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	/** The Constant CAN_NOT_PARSE_INTO_LONG. */
	public static final String CAN_NOT_PARSE_INTO_LONG = "can not parse into Long";

	/**
	 * Instantiates a new parsing exception.
	 *
	 * @param message
	 *            the message
	 */
	public ParsingException(String message) {
		super(message);
	}

}
