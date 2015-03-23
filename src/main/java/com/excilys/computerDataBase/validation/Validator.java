package com.excilys.computerDataBase.validation;

import org.apache.commons.validator.routines.DateValidator;

import com.excilys.computerDataBase.model.Computer;

public class Validator {

	public static final String WRONG_DATE_FORMAT = "unable to convert to date";

	public static boolean validateDate(String inputString) {
		return DateValidator.getInstance().isValid(inputString,
				"yyyy-MM-dd HH:mm:ss");
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

	public static boolean isComputerIdCorrect(Long computerId) {
		if (computerId == null) {
			return false;
		} else if (computerId <= new Long(0)) {
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

	public static boolean isNameForSearchCorrect(String string) {
		if (string == null) {
			return false;
		} else if ("".equals(string.trim())) {
			return false;
		} else {
			return true;
		}
	}
}