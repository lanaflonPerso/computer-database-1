/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.validation;

import org.apache.commons.validator.routines.DateValidator;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.sort.SortCriteria;
import com.excilys.computerdatabase.util.DateFormat;

/**
 * The Class Validator.
 */
public class Validator {

	/** The Constant WRONG_DATE_FORMAT. */
	public static final String WRONG_DATE_FORMAT = "unable to convert to date";
	
	/** The Constant INVALID_COMPUTER. */
	public static final String INVALID_COMPUTER = "computer is invalid";
	
	/** The Constant INVALID_COMPUTER_ID. */
	public static final String INVALID_COMPUTER_ID = "computer's id is invalid";
	
	/** The Constant INVALID_PARAMETER. */
	public static final String INVALID_PARAMETER = "invalid parameters";
	
	/** The Constant INVALID_COMPANY. */
	public static final String INVALID_COMPANY = "company is invalid";
	
	/** The Constant INVALID_COMPANY_ID. */
	public static final String INVALID_COMPANY_ID = "company's id is invalid";
	
	/** The Constant INVALID_BOUND. */
	public static final String INVALID_BOUND = "invalid bound";
	
	/** The Constant INVALID_STRING_FOR_SEARCH. */
	public static final String INVALID_STRING_FOR_SEARCH = "invalid string for search";
	
	/** The Constant INVALID_SORT_CRITERIA. */
	public static final String INVALID_SORT_CRITERIA = "invalid sort criteria";

	/**
	 * Checks if is date valid.
	 *
	 * @param date the date
	 * @return true, if is date valid
	 */
	public static boolean isDateValid(String date) {
		return isDateValid(date, DateFormat.ENGLISH);
	}

	/**
	 * Checks if is date valid.
	 *
	 * @param date the date
	 * @param dateFormat the date format
	 * @return true, if is date valid
	 */
	public static boolean isDateValid(String date, DateFormat dateFormat) {
		return isDateValid(date, dateFormat.toString());
	}

	/**
	 * Checks if is date valid.
	 *
	 * @param date the date
	 * @param pattern the pattern
	 * @return true, if is date valid
	 */
	public static boolean isDateValid(String date, String pattern){
		return DateValidator.getInstance().isValid(date, pattern);
	}
	
	/**
	 * Checks if is computer correct.
	 *
	 * @param computer the computer
	 * @return true, if is computer correct
	 */
	public static boolean isComputerCorrect(Computer computer) {
		if (computer == null) {
			return false;
		} else if (computer.getName() == null) {
			return false;
		} else if ("".equals(computer.getName().trim())) {
			return false;
		} else if (computer.getCompany() == null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Checks if is id correct.
	 *
	 * @param id the id
	 * @return true, if is id correct
	 */
	public static boolean isIdCorrect(Long id) {
		if (id == null) {
			return false;
		} else if (id <= new Long(0)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Checks if is date from to correct.
	 *
	 * @param from the from
	 * @param to the to
	 * @return true, if is date from to correct
	 */
	public static boolean isDateFromToCorrect(Long from, Long to) {
		if (from == null || to == null) {
			return false;
		} else if (from < 0L) {
			return false;
		} else if (to < 0L) {
			return false;
		} else if (from > to) {
			return false;
		} else if (from.equals(to)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Checks if is string for search correct.
	 *
	 * @param string the string
	 * @return true, if is string for search correct
	 */
	public static boolean isStringForSearchCorrect(String string) {
		if (string == null) {
			return false;
		} else if ("".equals(string.trim())) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Checks if is company correct.
	 *
	 * @param company the company
	 * @return true, if is company correct
	 */
	public static boolean isCompanyCorrect(Company company) {
		if (company == null) {
			return false;
		} else if (company.getName() == null) {
			return false;
		} else if ("".equals(company.getName().trim())) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Checks if is sort criteria correct.
	 *
	 * @param sortCriteria the sort criteria
	 * @return true, if is sort criteria correct
	 */
	public static boolean isSortCriteriaCorrect(SortCriteria sortCriteria) {
		if (sortCriteria == null) {
			return false;
		} else if (sortCriteria.getSortColumn() == null) {
			return false;
		} else if (sortCriteria.getSortDirection() == null) {
			return false;
		} else {
			return true;
		}
	}

}