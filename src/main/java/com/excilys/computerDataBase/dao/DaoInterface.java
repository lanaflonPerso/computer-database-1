package com.excilys.computerDataBase.dao;

import java.util.List;

/**
 * The Interface CommonDaoInterface.
 *
 * @param <T>
 *            the generic type
 */
public interface DaoInterface<T> {

	public default void create(T t) {
		throw new UnsupportedOperationException();
	}

	public void delete(Long id);

	public default void update(T t) {
		throw new UnsupportedOperationException();
	}

	public default T getById(Long index) {
		throw new UnsupportedOperationException();
	}

	public List<T> getAll();

	public List<T> getAll(Long from, Long to);

	public Long getNumberOfElement();

}
