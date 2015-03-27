/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.excilys.computerdatabase.exception.DaoException;

public interface CommonMapper <Model, Dto>{
	Model mapResultSetToModel (ResultSet resultSet);
	Dto mapModelToDto (Model model);
	Model mapDtoToModel (Dto dot);	
	
	default List<Model> mapListResultSetToModel(ResultSet resultSet) {
		List<Model> list = new ArrayList<Model>();
		try {
			while (resultSet.next()) {
				list.add(mapResultSetToModel(resultSet));
			}
		} catch (SQLException e) {
			throw new DaoException(DaoException.CAN_NOT_GET_ELEMENT);
		}
		return list;
	}
	
	default List<Dto> mapListModelToDto(List<Model> list) {
		return list.stream().map(this::mapModelToDto).collect(Collectors.toList());
	}
	
	default List<Model> mapListDtoToModel(List<Dto> list) {
		return list.stream().map(this::mapDtoToModel).collect(Collectors.toList());
	}
	
}
