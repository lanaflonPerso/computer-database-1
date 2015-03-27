/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.dao;

import java.util.List;

import com.excilys.computerdatabase.sort.SortCriteria;

public interface DaoInterface<T> {

	public void create(T t);

	public default void update(T t) {
		throw new UnsupportedOperationException();
	}

	public List<T> getAll(SortCriteria sortCriteria);

	public List<T> getAll(Long from, Long to, SortCriteria sortCriteria);

	public void delete(Long id);

	public T getById(Long id);

	public Long getNumberOfElement();

}
