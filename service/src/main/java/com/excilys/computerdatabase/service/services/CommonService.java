/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.service.services;

import java.util.List;

import com.excilys.computerdatabase.sort.SortCriteria;

/**
 * The Interface CommonService.
 *
 * @param <T> the generic type
 */
public interface CommonService<T> {

	/**
	 * Gets the number of element.
	 *
	 * @return the number of element
	 */
	public Long getNumberOfElement();
	
	/**
	 * List.
	 *
	 * @param sortCriteria the sort criteria
	 * @return the list
	 */
	public List<T> list(SortCriteria sortCriteria);

	/**
	 * List.
	 *
	 * @return the list
	 */
	public default List<T> list() {
		return list(new SortCriteria());
	}
	
	/**
	 * List.
	 *
	 * @param from the from
	 * @param to the to
	 * @param sortCriteria the sort criteria
	 * @return the list
	 */
	public List<T> list(Long from, Long to, SortCriteria sortCriteria);
	
	/**
	 * List.
	 *
	 * @param from the from
	 * @param to the to
	 * @return the list
	 */
	public default List<T> list(Long from, Long to) {
		return list(from, to, new SortCriteria());
	}
	
	/**
	 * Gets the by id.
	 *
	 * @param id the id
	 * @return the by id
	 */
	public T getById(Long id);

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
	public void update(T t);

	/**
	 * Delete.
	 *
	 * @param id the id
	 */
	public void delete(Long id);
	
}
