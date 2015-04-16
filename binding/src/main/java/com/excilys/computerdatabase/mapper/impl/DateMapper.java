package com.excilys.computerdatabase.mapper.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.excilys.computerdatabase.exception.ParsingException;
import com.excilys.computerdatabase.util.DateFormat;
import com.excilys.computerdatabase.validation.Validator;

public class DateMapper {
	
	public static LocalDateTime exctractFromString(String date, DateFormat dateFormat) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat.toString());
		if (date == null) {
			return null;
		} else if ("".equals(date.trim())) {
			return null;
		} else if (Validator.isDateValid(date, dateFormat)) {
			return LocalDateTime.parse(date, dateTimeFormatter);
		} else {
			throw new ParsingException(Validator.WRONG_DATE_FORMAT);
		}
	}
	
	public static String convertIntoString(LocalDateTime localDateTime, DateFormat dateFormat) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat.toString());
		if(localDateTime == null) {
			return null;
		}
		return localDateTime.format(dateTimeFormatter);
	}

	public static String convertIntoString(LocalDateTime localDateTime) {
		return convertIntoString(localDateTime, DateFormat.ENGLISH);
	}

	public static LocalDateTime exctractFromString(String date) {
		return exctractFromString(date, DateFormat.ENGLISH);
	}
	
	public static String convertString(String currentDate, DateFormat dateFormatFrom, DateFormat dateFormatTo) {
		LocalDateTime localDateTime = DateMapper.exctractFromString(currentDate, dateFormatFrom);
		return DateMapper.convertIntoString(localDateTime, dateFormatTo);
	}
}
