package com.excilys.computerdatabase.service.services;

import com.excilys.computerdatabase.sort.SortCriteria;

import java.util.List;

/**
 * @author Vincent Galloy
 *         The Interface CommonService.
 */
public interface CommonService<T> {

    /**
     * Gets the number of element.
     *
     * @return the number of element
     */
    Long getNumberOfElement();

    /**
     * List.
     *
     * @param sortCriteria the sort criteria
     * @return the list
     */
    List<T> list(SortCriteria sortCriteria);

    /**
     * List.
     *
     * @param from         the from
     * @param to           the to
     * @param sortCriteria the sort criteria
     * @return the list
     */
    List<T> list(Long from, Long to, SortCriteria sortCriteria);

    /**
     * List.
     *
     * @param from the from
     * @param to   the to
     * @return the list
     */
    default List<T> list(Long from, Long to) {
        return list(from, to, new SortCriteria());
    }

    /**
     * Gets the by id.
     *
     * @param id the id
     * @return the by id
     */
    T getById(Long id);

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
     * Delete.
     *
     * @param id the id
     */
    void delete(Long id);
}
