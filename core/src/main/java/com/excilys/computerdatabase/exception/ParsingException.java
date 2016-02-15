package com.excilys.computerdatabase.exception;

/**
 * @author Vincent Galloy
 *         The Class ParsingException.
 */
public class ParsingException extends RuntimeException {
    private static final long serialVersionUID = 7986802207951025731L;
    public static final String CAN_NOT_PARSE_INTO_LONG = "can not parse into Long";

    /**
     * Instantiates a new parsing exception.
     *
     * @param message the message
     */
    public ParsingException(String message) {
        super(message);
    }
}
