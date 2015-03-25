package com.excilys.computerDataBase.service;

import java.util.List;

import com.excilys.computerDataBase.dao.sort.SortCriteria;

/**
 * The Interface ServiceInterface.
 */
public interface ServiceInterface<T> {

	public Long getNumberOfElement();
	
	public List<T> list(SortCriteria sortCriteria);

	public List<T> list(Long from, Long to, SortCriteria sortCriteria);
	
	public default T getById(Long id) {
		throw new UnsupportedOperationException();
	}

	public default void create(T t) {
		throw new UnsupportedOperationException();
	}

	public default void update(T t) {
		throw new UnsupportedOperationException();
	}

	public void delete(Long id);
	
}
