package com.excilys.computerdatabase.webservice;

import java.util.List;

import javax.ws.rs.core.Response;

public interface CommonRessource <T>{
	public List<T> getAll();
	public T getById(Long id);
	public T create(T t);
	public default T update(T t) {
		throw new UnsupportedOperationException();
	}
	public Response delete(Long id);
}
