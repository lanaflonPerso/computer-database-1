/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.sort;

/**
 * The Enum SortDirection.
 */
public enum SortDirection {
	
	/** The asc. */
	ASC, 
 /** The desc. */
 DESC;

	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	public String toString() {
		switch (this) {
		case ASC:
			return "ASC";
		case DESC:
			return "DESC";
		default:
			return "ASC";
		}
	}

	/**
	 * Builds the.
	 *
	 * @param string the string
	 * @return the sort direction
	 */
	public static SortDirection build(String string) {
		if (string == null) {
			return ASC;
		}
		switch (string) {
		case "":
		case "ASC":
			return ASC;
		case "DESC":
			return DESC;
		default:
			return ASC;
		}
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
