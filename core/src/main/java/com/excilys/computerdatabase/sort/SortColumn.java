/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.sort;

public enum SortColumn {
	ID, COMPUTER_NAME, INTRODUCED_DATE, DISCONTINUED_DATE, COMPANY_NAME;

	public String toString() {
		switch (this) {
		case ID:
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

	public String toPrint() {
		switch (this) {
		case ID:
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
	
	
	public static SortColumn build(String string) {
		if (string == null) {
			return ID;
		}
		switch (string) {
		case "COMPUTER_ID":
			return ID;
		case "COMPUTER_NAME":
			return COMPUTER_NAME;
		case "INTRODUCED_DATE":
			return INTRODUCED_DATE;
		case "DISCONTINUED_DATE":
			return DISCONTINUED_DATE;
		case "COMPANY_NAME":
			return COMPANY_NAME;
		default:
			return ID;
		}
	}

	
	
}
