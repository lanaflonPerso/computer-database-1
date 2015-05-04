/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.sort;

/**
 * The Enum SortColumn.
 */
public enum SortColumn {
	
	/** The computer id. */
	COMPUTER_ID, 
 /** The computer name. */
 COMPUTER_NAME, 
 /** The introduced date. */
 INTRODUCED_DATE, 
 /** The discontinued date. */
 DISCONTINUED_DATE, 
 /** The company name. */
 COMPANY_NAME;

	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	public String toString() {
		switch (this) {
		case COMPUTER_ID:
			return "computer.id";
		case COMPUTER_NAME:
			return "computer.name";
		case INTRODUCED_DATE:
			return "computer.introduced";
		case DISCONTINUED_DATE:
			return "computer.discontinued";
		case COMPANY_NAME:
			return "company.name";
		default:
			return null;
		}
	}

	/**
	 * To print.
	 *
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
	 * Builds the.
	 *
	 * @param string the string
	 * @return the sort column
	 */
	public static SortColumn build(String string) {
		if (string == null) {
			return COMPUTER_ID;
		}
		switch (string) {
		case "":
		case "COMPUTER_ID":
			return COMPUTER_ID;
		case "COMPUTER_NAME":
			return COMPUTER_NAME;
		case "INTRODUCED_DATE":
			return INTRODUCED_DATE;
		case "DISCONTINUED_DATE":
			return DISCONTINUED_DATE;
		case "COMPANY_NAME":
			return COMPANY_NAME;
		default:
			return COMPUTER_ID;
		}
	}

	
	
}
