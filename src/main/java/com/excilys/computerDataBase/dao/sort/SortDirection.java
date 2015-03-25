package com.excilys.computerDataBase.dao.sort;

public enum SortDirection {
	ASC, DESC;

	public String toString() {
		switch (this) {
		case ASC:
			return "ASC, compu.id ASC";
		case DESC:
			return "DESC, compu.id ASC";
		default:
			return "ASC, compu.id ASC";
		}
	}

	public static SortDirection build(String string) {
		if (string == null) {
			return ASC;
		}
		switch (string) {
		case "":
			return ASC;
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
