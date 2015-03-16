package com.excilys.computerDataBase.exception;

// TODO: Auto-generated Javadoc
/**
 * The Class UnableToInsertElementException.
 */
public class UnableToInsertElementException extends DataBaseException {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;


	/** The Constant NULL_COMPUTER. */
	public static final String NULL_COMPUTER = "computer can not be null";
	
	/** The Constant CAN_NOT_INSERT. */
	public static final String CAN_NOT_INSERT = "can't insert the element into the dadabase";
	
	/** The Constant NULL_COMPANY_ID. */
	public static final String NULL_COMPANY_ID = "company_id can not be null";
	
	/**
	 * Instantiates a new unable to insert element exception.
	 */
	public UnableToInsertElementException() {
		super();
	}

	/**
	 * Instantiates a new unable to insert element exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 * @param enableSuppression the enable suppression
	 * @param writableStackTrace the writable stack trace
	 */
	public UnableToInsertElementException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * Instantiates a new unable to insert element exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public UnableToInsertElementException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new unable to insert element exception.
	 *
	 * @param message the message
	 */
	public UnableToInsertElementException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new unable to insert element exception.
	 *
	 * @param cause the cause
	 */
	public UnableToInsertElementException(Throwable cause) {
		super(cause);
	}
}
