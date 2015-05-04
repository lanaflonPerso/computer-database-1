/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.persistence.dao;

import java.util.List;

import com.excilys.computerdatabase.sort.SortCriteria;

/**
 * The Interface CommonDao.
 *
 * @param <T> the generic type
 */
public interface CommonDao<T> {

	/**
	 * Creates the.
	 *
	 * @param t the t
	 */
	public void create(T t);

	/**
	 * Update.
	 *
	 * @param t the t
	 */
	public default void update(T t) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Gets the all.
	 *
	 * @param sortCriteria the sort criteria
	 * @return the all
	 */
	public List<T> getAll(SortCriteria sortCriteria);

	/**
	 * Gets the all.
	 *
	 * @param from the from
	 * @param to the to
	 * @param sortCriteria the sort criteria
	 * @return the all
	 */
	public List<T> getAll(Long from, Long to, SortCriteria sortCriteria);

	/**
	 * Delete.
	 *
	 * @param id the id
	 */
	public void delete(Long id);

	/**
	 * Gets the by id.
	 *
	 * @param id the id
	 * @return the by id
	 */
	public T getById(Long id);

	/**
	 * Gets the number of element.
	 *
	 * @return the number of element
	 */
	public Long getNumberOfElement();

}
