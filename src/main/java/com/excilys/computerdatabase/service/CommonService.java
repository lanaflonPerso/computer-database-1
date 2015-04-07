/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.excilys.computerdatabase.sort.SortCriteria;

@Service
public interface CommonService<T> {

	public Long getNumberOfElement();
	
	public List<T> list(SortCriteria sortCriteria);

	public default List<T> list() {
		return list(new SortCriteria());
	}
	
	public List<T> list(Long from, Long to, SortCriteria sortCriteria);
	
	public default List<T> list(Long from, Long to) {
		return list(from, to, new SortCriteria());
	}
	
	public T getById(Long id);

	public void create(T t);

	public default void update(T t) {
		throw new UnsupportedOperationException();
	}

	public void delete(Long id);
	
}
