/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.sort;

public enum SortDirection {
	ASC, DESC;

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