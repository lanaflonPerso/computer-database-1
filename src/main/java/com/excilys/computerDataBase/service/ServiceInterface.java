package com.excilys.computerDataBase.service;

import java.util.List;

/**
 * The Interface ServiceInterface.
 */
public interface ServiceInterface<T> {

	public Long getNumberOfElement();
	
	public List<T> list();

	public List<T> list(Long from, Long to);
	
	public default T details(Long id) {
		throw new UnsupportedOperationException();
	}

	public default void create(T t) {
		throw new UnsupportedOperationException();
	}

	public default void update(T t) {
		throw new UnsupportedOperationException();
	}

	public default void delete(Long id) {
		throw new UnsupportedOperationException();
	}
	
}
