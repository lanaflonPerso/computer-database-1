package com.excilys.computerDataBase.mapper;

import java.sql.ResultSet;

public interface MapperInterface <T>{
	public T map (ResultSet resultSet);
}
