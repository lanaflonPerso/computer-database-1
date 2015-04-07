/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.exception;

public class ServiceException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public static final String INVALID_COMPUTER = "computer is invalid";
	public static final String INVALID_COMPUTER_ID = "computer's id is invalid";
	public static final String INVALID_PARAMETER = "invalid parameters";
	public static final String CAN_NOT_ROLLBACK_TRANSACTION = "can not rollback transaction";
	public static final String INVALID_COMPANY = "company is invalid";
	public static final String INVALID_COMPANY_ID = "company's id is invalid";
	
	public ServiceException() {
		super();
	}

	public ServiceException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

}
