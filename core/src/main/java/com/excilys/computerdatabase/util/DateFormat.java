package com.excilys.computerdatabase.util;

/**
 * The Enum DateFormat.
 */
public enum DateFormat {
	
	/** The french. */
	FRENCH, 
 /** The english. */
 ENGLISH;

	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString(){
		switch (this) {
		case FRENCH:
			return "dd/MM/yyyy";
		case ENGLISH:
			return "yyyy-MM-dd";
		default:
			return null;
		}
	}
	
}
