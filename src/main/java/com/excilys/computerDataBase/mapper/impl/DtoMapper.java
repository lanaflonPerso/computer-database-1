package com.excilys.computerDataBase.mapper.impl;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerDataBase.dto.ComputerDto;
import com.excilys.computerDataBase.mapper.DtoMapperInterface;
import com.excilys.computerDataBase.model.Computer;

public enum DtoMapper implements DtoMapperInterface {
	INSTANCE;

	private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");

	@Override
	public ComputerDto mapComputer(Computer computer) {
		ComputerDto computerDto = new ComputerDto();
		computerDto.setId(computer.getId());
		computerDto.setCompany(computer.getCompany());
		computerDto.setName(computer.getName());
		if (computer.getDiscontinued() != null) {
			computerDto.setDiscontinued(computer.getDiscontinued().format(
					dateTimeFormatter));
		} else {
			computerDto.setDiscontinued(null);
		}
		if (computer.getIntroduced() != null) {
			computerDto.setIntroduced(computer.getIntroduced().format(
					dateTimeFormatter));
		} else {
			computerDto.setIntroduced(null);
		}
		return computerDto;
	}

	@Override
	public List<ComputerDto> mapComputers(List<Computer> computers) {
		List<ComputerDto> computerDtos = new ArrayList<ComputerDto>();
		for(Computer computer : computers) {
			computerDtos.add(mapComputer(computer));
		}
		return computerDtos;
	}

}
