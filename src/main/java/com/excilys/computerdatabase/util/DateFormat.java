package com.excilys.computerdatabase.util;

public enum DateFormat {
	FRENCH, ENGLISH;

	@Override
	public String toString(){
		switch (this) {
		case FRENCH:
			return "dd/MM/yyyy HH:mm:ss";
		case ENGLISH:
			return "yyyy-MM-dd HH:mm:ss";
		default:
			return null;
		}
	}
	
}
