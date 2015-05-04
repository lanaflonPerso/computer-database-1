/**
 * @Author Vincent Galloy
 * 
 */
package com.excilys.computerdatabase.dto.validator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * The Class DateValidator.
 */
@Component
public class DateValidator implements ConstraintValidator<Date, String> {

	/** The message source. */
	@Autowired
	private MessageSource messageSource;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation
	 * )
	 */
	@Override
	public void initialize(Date constraintAnnotation) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.validation.ConstraintValidator#isValid(java.lang.Object,
	 * javax.validation.ConstraintValidatorContext)
	 */
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			return false;
		}
		if (value.isEmpty()) {
			return true;
		}
		return isCorrectDate(value, getPattern());
	}

	/**
	 * Checks if is correct date.
	 *
	 * @param date
	 *          the date
	 * @param pattern
	 *          the pattern
	 * @return true, if is correct date
	 */
	private boolean isCorrectDate(String date, String pattern) {
		if (org.apache.commons.validator.routines.DateValidator.getInstance().isValid(date, pattern)) {
			LocalDateTime localDateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern(pattern));
			LocalDateTime localDateTimeAfter = LocalDateTime.parse("1970-01-01 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
			LocalDateTime localDateTimeBefore = LocalDateTime.parse("2038-01-01 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
			return localDateTime.isAfter(localDateTimeAfter) && localDateTime.isBefore(localDateTimeBefore);
		} else {
			return false;
		}
	}

	/**
	 * Gets the pattern.
	 *
	 * @return the pattern
	 */
	private String getPattern() {
		String str;
		Locale userLocale = LocaleContextHolder.getLocale();
		if (messageSource != null) {
			str = messageSource.getMessage("date.pattern", null, userLocale);
		} else {
			str = "yyyy-MM-dd HH:mm:ss";
		}
		return str;
	}
}