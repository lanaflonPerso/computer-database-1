/**
 * @Author Vincent Galloy
 * 
 */
package com.excilys.computerdatabase.dto.validator;

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

	/* (non-Javadoc)
	 * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
	 */
	@Override
	public void initialize(Date constraintAnnotation) {
	}

	/* (non-Javadoc)
	 * @see javax.validation.ConstraintValidator#isValid(java.lang.Object, javax.validation.ConstraintValidatorContext)
	 */
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			return false;
		}
		if (value.isEmpty()) {
			return true;
		}
		if (isCorrectDate(value, getPattern())) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is correct date.
	 *
	 * @param date the date
	 * @param pattern the pattern
	 * @return true, if is correct date
	 */
	private boolean isCorrectDate(String date, String pattern) {
		return org.apache.commons.validator.routines.DateValidator.getInstance().isValid(date, pattern);
	}
	
	/**
	 * Gets the pattern.
	 *
	 * @return the pattern
	 */
	private String getPattern() {
		String str;
		Locale userLocale = LocaleContextHolder.getLocale();
		if(messageSource != null) {
			str = messageSource.getMessage("date.pattern", null, userLocale);
		}
		else {
			str = "yyyy-MM-dd HH:mm:ss";
		}
		return str;
	}
}