package com.excilys.computerdatabase.util;

/**
 * @author Vincent Galloy
 *         The Enum DateFormat.
 */
public enum DateFormat {
    FRENCH("dd/MM/yyyy"),
    ENGLISH("yyyy-MM-dd");
    private final String value;

    DateFormat(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
