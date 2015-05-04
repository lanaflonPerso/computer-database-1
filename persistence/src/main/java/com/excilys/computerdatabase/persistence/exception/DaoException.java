/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.persistence.exception;

/**
 * The Class DataBaseException.
 */
/**
 * @author excilys
 *
 */
public class DaoException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant CAN_NOT_CREATE_CONNECTION. */
	public static final String CAN_NOT_CREATE_CONNECTION = "can not create connection";

	/** The Constant CAN_NOT_UPDATE_ELEMENT. */
	public static final String CAN_NOT_UPDATE_ELEMENT = "can not update the element";

	/** The Constant CAN_NOT_DELETE_ELEMENT. */
	public static final String CAN_NOT_DELETE_ELEMENT = "can not delete the element";

	/** The Constant CAN_NOT_GET_ELEMENT. */
	public static final String CAN_NOT_GET_ELEMENT = "can not get the element";

	/** The Constant CAN_NOT_INSERT_ELEMENT. */
	public static final String CAN_NOT_INSERT_ELEMENT = "can not insert the element";

	/** The Constant CAN_NOT_CLOSE_CONNECTION. */
	public static final String CAN_NOT_CLOSE_CONNECTION = "can not close connection";

	/** The Constant CAN_NOT_CLOSE_STATEMENT. */
	public static final String CAN_NOT_CLOSE_STATEMENT = "can not close statement";

	/** The Constant CAN_NOT_COMMIT_TRANSACTION. */
	public static final String CAN_NOT_COMMIT_TRANSACTION = "can not commit transaction";

	/** The Constant CAN_NOT_ROLLBACK_TRANSACTION. */
	public static final String CAN_NOT_ROLLBACK_TRANSACTION = "can not rollback transaction";


	/**
	 * Instantiates a new data base exception.
	 */
	public DaoException() {
		super();
	}

	/**
	 * Instantiates a new data base exception.
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
	public DaoException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * Instantiates a new data base exception.
	 *
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new data base exception.
	 *
	 * @param message
	 *            the message
	 */
	public DaoException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new data base exception.
	 *
	 * @param cause
	 *            the cause
	 */
	public DaoException(Throwable cause) {
		super(cause);
	}

}
