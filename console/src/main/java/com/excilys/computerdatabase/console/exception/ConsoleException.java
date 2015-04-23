package com.excilys.computerdatabase.console.exception;

public class ConsoleException extends RuntimeException {

	private static final long serialVersionUID = -4752359502580121210L;

	public ConsoleException() {
		super();
	}

	public ConsoleException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ConsoleException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConsoleException(String message) {
		super(message);
	}

	public ConsoleException(Throwable cause) {
		super(cause);
	}

}
