package com.excilys.computerdatabase.mapper.impl;

import com.excilys.computerdatabase.exception.ParsingException;
import com.excilys.computerdatabase.util.DateFormat;
import com.excilys.computerdatabase.validation.Validator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Vincent Galloy
 */
public interface DateMapper {

    static LocalDateTime extractFromString(String date, DateFormat dateFormat) {
        return extractFromString(date, dateFormat.toString());
    }

    static LocalDateTime extractFromString(String date, String pattern) {
        if (date == null) {
            return null;
        } else if ("".equals(date.trim())) {
            return null;
        } else if (Validator.isDateValid(date, pattern)) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
            LocalDate localDate = LocalDate.parse(date, dateTimeFormatter);
            return LocalDateTime.of(localDate.getYear(), localDate.getMonth(), localDate.getDayOfMonth(), 0, 0);
        } else {
            throw new ParsingException(Validator.WRONG_DATE_FORMAT);
        }

    }

    static String convertIntoString(LocalDateTime localDateTime, DateFormat dateFormat) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat.toString());
        if (localDateTime == null) {
            return null;
        }
        return localDateTime.format(dateTimeFormatter);
    }

    static String convertIntoString(LocalDateTime localDateTime) {
        return convertIntoString(localDateTime, DateFormat.ENGLISH);
    }

    static LocalDateTime extractFromString(String date) {
        return extractFromString(date, DateFormat.ENGLISH);
    }

    static String convertString(String currentDate, DateFormat dateFormatFrom, DateFormat dateFormatTo) {
        LocalDateTime localDateTime = DateMapper.extractFromString(currentDate, dateFormatFrom);
        return DateMapper.convertIntoString(localDateTime, dateFormatTo);
    }
}
