package com.excilys.computerdatabase.mapper;

import java.util.List;
import java.util.stream.Collectors;

public interface Mapper <Model, Dto>{
	public Dto mapFromModel(Model model);

	public Model mapToModel(Dto dto);

	default List<Dto> mapListFromModel(List<Model> list) {
		return list.stream().map(this::mapFromModel).collect(Collectors.toList());
	}

	default List<Model> mapListToModel(List<Dto> list) {
		return list.stream().map(this::mapToModel).collect(Collectors.toList());
	}
}
