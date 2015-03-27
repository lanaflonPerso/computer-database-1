/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.sort;

public enum SortColumn {
	NULL, COMPUTER_NAME, INTRODUCED_DATE, DISCONTINUED_DATE, COMPANY_NAME;

	public String toString() {
		switch (this) {
		case NULL:
			return null;
		case COMPUTER_NAME:
			return "compu.name";
		case INTRODUCED_DATE:
			return "compu.introduced";
		case DISCONTINUED_DATE:
			return "compu.discontinued";
		case COMPANY_NAME:
			return "compa.name";
		default:
			return null;
		}
	}

	public String toPrint() {
		switch (this) {
		case NULL:
			return "";
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
			return NULL;
		}
		switch (string) {
		case "":
			return NULL;
		case "COMPUTER_NAME":
			return COMPUTER_NAME;
		case "INTRODUCED_DATE":
			return INTRODUCED_DATE;
		case "DISCONTINUED_DATE":
			return DISCONTINUED_DATE;
		case "COMPANY_NAME":
			return COMPANY_NAME;
		default:
			return null;
		}
	}

	
	
}
