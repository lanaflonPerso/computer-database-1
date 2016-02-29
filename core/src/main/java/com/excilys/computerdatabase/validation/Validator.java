package com.excilys.computerdatabase.validation;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.sort.SortCriteria;
import com.excilys.computerdatabase.util.DateFormat;
import org.apache.commons.validator.routines.DateValidator;

/**
 * @author Vincent Galloy
 *         The Class Validator.
 */
public interface Validator {
    String WRONG_DATE_FORMAT = "unable to convert to date";
    String INVALID_COMPUTER = "computer is invalid";
    String INVALID_COMPUTER_ID = "computer's id is invalid";
    String INVALID_COMPANY = "company is invalid";
    String INVALID_COMPANY_ID = "company's id is invalid";
    String INVALID_BOUND = "invalid bound";
    String INVALID_STRING_FOR_SEARCH = "invalid string for search";
    String INVALID_SORT_CRITERIA = "invalid sort criteria";

    /**
     * Checks if is date valid.
     *
     * @param date the date
     * @return true, if is date valid
     */
    static boolean isDateValid(String date) {
        return isDateValid(date, DateFormat.ENGLISH);
    }

    /**
     * Checks if is date valid.
     *
     * @param date       the date
     * @param dateFormat the date format
     * @return true, if is date valid
     */
    static boolean isDateValid(String date, DateFormat dateFormat) {
        return isDateValid(date, dateFormat.toString());
    }

    /**
     * Checks if is date valid.
     *
     * @param date    the date
     * @param pattern the pattern
     * @return true, if is date valid
     */
    static boolean isDateValid(String date, String pattern) {
        return DateValidator.getInstance().isValid(date, pattern);
    }

    /**
     * Checks if is computer correct.
     *
     * @param computer the computer
     * @return true, if is computer correct
     */
    static boolean isComputerCorrect(Computer computer) {
        return computer != null && computer.getName() != null && !"".equals(computer.getName().trim()) && (computer.getCompany() == null || computer.getCompany().getId() != null);
    }

    /**
     * Checks if is id correct.
     *
     * @param id the id
     * @return true, if is id correct
     */
    static boolean isIdCorrect(Long id) {
        return id != null && id > new Long(0);
    }

    /**
     * Checks if is date from to correct.
     *
     * @param from the from
     * @param to   the to
     * @return true, if is date from to correct
     */
    static boolean isDateFromToCorrect(Long from, Long to) {
        return !(from == null || to == null) && from >= 0L && to >= 0L && from <= to && !from.equals(to);
    }

    /**
     * Checks if is string for search correct.
     *
     * @param string the string
     * @return true, if is string for search correct
     */
    static boolean isStringForSearchCorrect(String string) {
        return string != null && !"".equals(string.trim());
    }

    /**
     * Checks if is company correct for update.
     *
     * @param company the company
     * @return true, if is company correct
     */
    static boolean isCompanyCorrectForUpdate(Company company) {
        return isCompanyCorrectForCreate(company) && company.getId() != null;
    }

    /**
     * Checks if is company correct for create.
     *
     * @param company the company
     * @return true, if is company correct
     */
    static boolean isCompanyCorrectForCreate(Company company) {
        return company != null && company.getName() != null && !"".equals(company.getName().trim());
    }

    /**
     * Checks if is sort criteria correct.
     *
     * @param sortCriteria the sort criteria
     * @return true, if is sort criteria correct
     */
    static boolean isSortCriteriaCorrect(SortCriteria sortCriteria) {
        return sortCriteria != null && sortCriteria.getSortColumn() != null && sortCriteria.getSortDirection() != null;
    }
}