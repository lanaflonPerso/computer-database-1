/**
 * @Author Vincent Galloy
 * 
 */
package com.excilys.computerdatabase.webservice;

import java.util.List;

import javax.ws.rs.core.Response;

/**
 * The Interface CommonRessource.
 *
 * @param <T> the generic type
 */
public interface CommonRessource <T>{
	
	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	public List<T> getAll();
	
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
	 * @return the t
	 */
	public T create(T t);
	
	/**
	 * Update.
	 *
	 * @param t the t
	 * @return the t
	 */
	public default T update(T t) {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Delete.
	 *
	 * @param id the id
	 * @return the response
	 */
	public Response delete(Long id);
}
