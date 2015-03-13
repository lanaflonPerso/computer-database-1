package com.excilys.computerDataBase.dao;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Interface CommonDaoInterface.
 *
 * @param <T> the generic type
 */
public interface CommonDaoInterface <T> {
	
	/**
	 * Creates the.
	 *
	 * @param t the t
	 * @return the t
	 */
	public T create (T t);
	
	/**
	 * Delete.
	 *
	 * @param t the t
	 */
	public void delete (T t);
	
	/**
	 * Update.
	 *
	 * @param t the t
	 * @return the t
	 */
	public T update (T t);
	
	/**
	 * Gets the.
	 *
	 * @param index the index
	 * @return the t
	 */
	public T get(long index);
	
	/**
	 * Select all.
	 *
	 * @return the list
	 */
	public List<T> selectAll();
}
