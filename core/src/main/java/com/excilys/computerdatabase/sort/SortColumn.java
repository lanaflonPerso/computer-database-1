package com.excilys.computerdatabase.sort;

/**
 * @author Vincent Galloy
 *         The Enum SortColumn.
 */
public enum SortColumn {
    COMPUTER_ID("computer.id", "COMPUTER_ID"),
    COMPUTER_NAME("computer.name", "COMPUTER_NAME"),
    INTRODUCED_DATE("computer.introduced", "INTRODUCED_DATE"),
    DISCONTINUED_DATE("computer.discontinued", "DISCONTINUED_DATE"),
    COMPANY_NAME("company.name", "COMPANY_NAME");
    private final String value;
    private final String valueForPrint;

    SortColumn(String value, String valueForPrint) {
        this.value = value;
        this.valueForPrint = valueForPrint;
    }

    @Override
    public String toString() {
        return this.value;
    }

    /**
     * @return the string
     */
    public String toPrint() {
        return this.valueForPrint;
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
