/**
 * @Author Vincent Galloy
 */
package com.excilys.computerDataBase.dao;

import java.sql.Connection;
import java.util.List;

import com.excilys.computerDataBase.dao.sort.SortCriteria;
import com.excilys.computerDataBase.factory.ConnectionFactory;
import com.excilys.computerDataBase.util.DaoUtil;


public interface DaoInterface<T> {

	public void create(Connection connection, T t);

	public T getById(Connection connection, Long id);

	public Long getNumberOfElement(Connection connection);

	public default void update(Connection connection, T t) {
		throw new UnsupportedOperationException();
	}

	public void delete(Connection connection, Long id);

	public List<T> getAll(SortCriteria sortCriteria);

	public List<T> getAll(Long from, Long to, SortCriteria sortCriteria);

	public default void create(T t) {
		Connection connection = null;
		connection = ConnectionFactory.INSTANCE.createConnection();
		create(connection, t);
		DaoUtil.closeConnection(connection);
	}

	public default T getById(Long id) {
		Connection connection = null;
		connection = ConnectionFactory.INSTANCE.createConnection();
		T result = getById(connection, id);
		DaoUtil.closeConnection(connection);
		return result;
	}

	public default Long getNumberOfElement() {
		Connection connection = null;
		connection = ConnectionFactory.INSTANCE.createConnection();
		Long result = getNumberOfElement(connection);
		DaoUtil.closeConnection(connection);
		return result;
	}
	public default void update(T t) {
		Connection connection = null;
		connection = ConnectionFactory.INSTANCE.createConnection();
		update(connection, t);
		DaoUtil.closeConnection(connection);
	}
	public default void delete(Long id) {
		Connection connection = null;
		connection = ConnectionFactory.INSTANCE.createConnection();
		delete(connection, id);
		DaoUtil.closeConnection(connection);
	}

}
