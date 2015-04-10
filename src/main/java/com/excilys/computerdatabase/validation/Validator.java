/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.validation;

import org.apache.commons.validator.routines.DateValidator;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.sort.SortCriteria;
import com.excilys.computerdatabase.util.DateFormat;

public class Validator {

	public static final String WRONG_DATE_FORMAT = "unable to convert to date";
	public static final String INVALID_COMPUTER = "computer is invalid";
	public static final String INVALID_COMPUTER_ID = "computer's id is invalid";
	public static final String INVALID_PARAMETER = "invalid parameters";
	public static final String INVALID_COMPANY = "company is invalid";
	public static final String INVALID_COMPANY_ID = "company's id is invalid";
	public static final String INVALID_BOUND = "invalid bound";
	public static final String INVALID_STRING_FOR_SEARCH = "invalid string for search";
	public static final String INVALID_SORT_CRITERIA = "invalid sort criteria";

	public static boolean isDateValid(String date) {
		return isDateValid(date, DateFormat.ENGLISH);
	}

	public static boolean isDateValid(String date, DateFormat dateFormat) {
		return DateValidator.getInstance().isValid(date, dateFormat.toString());
	}

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

	public static boolean isIdCorrect(Long id) {
		if (id == null) {
			return false;
		} else if (id <= new Long(0)) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean isDateFromToCorrect(Long from, Long to) {
		if (from == null || to == null) {
			return false;
		} else if (from < 0L) {
			return false;
		} else if (to < 0L) {
			return false;
		} else if (from > to) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean isStringForSearchCorrect(String string) {
		if (string == null) {
			return false;
		} else if ("".equals(string.trim())) {
			return false;
		} else {
			return true;
		}
	}

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