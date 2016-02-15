package com.excilys.computerdatabase.sort;

/**
 * @author Vincent Galloy
 *         The Enum SortDirection.
 */
public enum SortDirection {
    ASC("ASC"),
    DESC("DESC");
    private final String value;

    SortDirection(String value) {
        this.value = value;
    }

    public String toString() {
        return value.toString();
    }

    /**
     * Builds the SortDirection from a String
     *
     * @param value the string
     * @return the SortDirection
     */
    public static SortDirection build(String value) {
        if (value == null) {
            return ASC;
        }
        for (SortDirection sortDirection : values()) {
            if (value.equals(sortDirection.toString())) {
                return sortDirection;
            }
        }
        return ASC;
    }

    /**
     * To print.
     *
     * @return the string
     */
    public String toPrint() {
        switch (this) {
            case ASC:
                return "ASC";
            case DESC:
                return "DESC";
            default:
                return "ASC";
        }
    }
}
