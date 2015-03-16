package com.excilys.computerDataBase.exception;

// TODO: Auto-generated Javadoc
/**
 * The Class GetException.
 */
public class GetException extends DataBaseException {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	/** The Constant CAN_NOT_GET_COMPUTER. */
	public static final String CAN_NOT_GET_COMPUTER = "unable to get the computer";
	
	/** The Constant NO_ITEM_FOUND. */
	public static final String NO_ITEM_FOUND = "no computer with this id";

	/**
	 * Instantiates a new gets the exception.
	 */
	public GetException() {
		super();
	}

	/**
	 * Instantiates a new gets the exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 * @param enableSuppression the enable suppression
	 * @param writableStackTrace the writable stack trace
	 */
	public GetException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new gets the exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public GetException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new gets the exception.
	 *
	 * @param message the message
	 */
	public GetException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new gets the exception.
	 *
	 * @param cause the cause
	 */
	public GetException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
