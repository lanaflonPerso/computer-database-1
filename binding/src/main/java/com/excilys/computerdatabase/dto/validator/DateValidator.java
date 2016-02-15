package com.excilys.computerdatabase.dto.validator;

import com.excilys.computerdatabase.mapper.impl.DateMapper;
import com.excilys.computerdatabase.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @author Vincent Galloy
 *         The Class DateValidator.
 */
@Component
public class DateValidator implements ConstraintValidator<Date, String> {
    @Autowired
    private MessageSource messageSource;

    @Override
    public void initialize(Date constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && (value.isEmpty() || isCorrectDate(value, getPattern()));
    }

    /**
     * Checks if is correct date.
     *
     * @param date    the date
     * @param pattern the pattern
     * @return true, if is correct date
     */
    private boolean isCorrectDate(String date, String pattern) {
        if (Validator.isDateValid(date, pattern)) {
            LocalDateTime localDateTime = DateMapper.extractFromString(date, pattern);
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