package com.excilys.computerdatabase.validator;

import java.util.Locale;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class DateValidator implements ConstraintValidator<Date, String> {
	@Autowired
	private MessageSource messageSource;

	@Override
	public void initialize(Date constraintAnnotation) {
	}

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

	private boolean isCorrectDate(String date, String pattern) {
		return org.apache.commons.validator.routines.DateValidator.getInstance().isValid(date, pattern);
	}
	
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