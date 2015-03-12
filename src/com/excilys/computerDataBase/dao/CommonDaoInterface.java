package com.excilys.computerDataBase.dao;

import java.sql.SQLException;
import java.util.List;

public interface CommonDaoInterface <T> {
	public void openConnection(String dataBase, String user, String password) throws SQLException;
	public void closeConnection();
	public T create (T t);
	public void delete (T t);
	public T update (T t);
	public T get(long index);
	public List<T> selectAll();
}
