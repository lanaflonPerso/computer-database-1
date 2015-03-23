package com.excilys.computerDataBase.mapper;

import java.util.List;

import com.excilys.computerDataBase.dto.ComputerDto;
import com.excilys.computerDataBase.model.Computer;

public interface DtoMapperInterface {
	public ComputerDto mapComputer (Computer computer);
	public List<ComputerDto> mapComputers (List<Computer> computers);
}
