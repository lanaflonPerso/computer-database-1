package com.excilys.computerdatabase.persistence.dao;

import com.excilys.computerdatabase.sort.SortCriteria;

import java.util.List;

/**
 * @author Vincent Galloy
 *         The Interface CommonDao.
 */
public interface CommonDao<T> {

    /**
     * Creates the.
     *
     * @param t the t
     */
    void create(T t);

    /**
     * Update.
     *
     * @param t the t
     */
    void update(T t);

    /**
     * Gets the all.
     *
     * @param sortCriteria the sort criteria
     * @return the all
     */
    List<T> getAll(SortCriteria sortCriteria);

    /**
     * Gets the all.
     *
     * @param from         the from
     * @param to           the to
     * @param sortCriteria the sort criteria
     * @return the all
     */
    List<T> getAll(Long from, Long to, SortCriteria sortCriteria);

    /**
     * Delete.
     *
     * @param id the id
     */
    void delete(Long id);

    /**
     * Gets the by id.
     *
     * @param id the id
     * @return the object by id
     */
    T getById(Long id);

    /**
     * Gets the number of element.
     *
     * @return the number of element
     */
    Long getNumberOfElement();
}
