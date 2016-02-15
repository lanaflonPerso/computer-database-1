package com.excilys.computerdatabase.sort;

/**
 * @author Vincent Galloy
 *         The Enum SortColumn.
 */
public enum SortColumn {
    COMPUTER_ID("computer.id"),
    COMPUTER_NAME("computer.name"),
    INTRODUCED_DATE("computer.introduced"),
    DISCONTINUED_DATE("computer.discontinued"),
    COMPANY_NAME("company.name");
    private final String value;

    SortColumn(String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }

    /**
     * @return the string
     */
    public String toPrint() {
        switch (this) {
            case COMPUTER_ID:
                return "COMPUTER_ID";
            case COMPUTER_NAME:
                return "COMPUTER_NAME";
            case INTRODUCED_DATE:
                return "INTRODUCED_DATE";
            case DISCONTINUED_DATE:
                return "DISCONTINUED_DATE";
            case COMPANY_NAME:
                return "COMPANY_NAME";
            default:
                return null;
        }
    }

    /**
     * Builds the SortColumn from a String
     *
     * @param value the string
     * @return the sort column
     */
    public static SortColumn build(String value) {
        if (value == null) {
            return COMPUTER_ID;
        }
        for (SortColumn sortColumn : values()) {
            if (value.equals(sortColumn.toString())) {
                return sortColumn;
            }
        }
        return COMPUTER_ID;
    }
}
