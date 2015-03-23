package com.excilys.computerDataBase.test.unitaire;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.excilys.computerDataBase.dto.ComputerDto;
import com.excilys.computerDataBase.mapper.impl.DtoMapper;
import com.excilys.computerDataBase.model.Company;
import com.excilys.computerDataBase.model.Computer;

public class TestMapper {

	DateTimeFormatter dateTimeFormatter = DateTimeFormatter
			.ofPattern("yyyy-MM-dd HH:mm:ss");

	@Test
	public void testMapperDtoOk() {
		Computer computer = new Computer();
		DtoMapper.INSTANCE.mapComputer(computer);
	}

	@Test
	public void testMapperDtoOk2() {
		Computer computer = new Computer(null, "name", LocalDateTime.parse(
				"2000-10-10 10:10:10", dateTimeFormatter), null, new Company(
				null, "name"));
		ComputerDto computerDto = DtoMapper.INSTANCE.mapComputer(computer);
		assertThat(computerDto.getIntroduced(), is("2000-10-10 10:10:10"));
	}

	@Test
	public void testMapperDtoOk3() {
		Computer computer = new Computer(null, "name", null,
				LocalDateTime.parse("2000-10-10 10:10:10", dateTimeFormatter),
				new Company(null, "name"));
		ComputerDto computerDto = DtoMapper.INSTANCE.mapComputer(computer);
		assertThat(computerDto.getIntroduced(), nullValue());
		assertThat(computerDto.getDiscontinued(), is("2000-10-10 10:10:10"));
	}
	
	@Test
	public void testMapperDtoListOk() {
		Computer computer = new Computer(null, "name", null,
				LocalDateTime.parse("2000-10-10 10:10:10", dateTimeFormatter),
				new Company(null, "name"));
		List<Computer> computers = new ArrayList<Computer>();
		computers.add(computer);
		computers.add(new Computer());
		
		List<ComputerDto> computerDtos = DtoMapper.INSTANCE.mapComputers(computers);
		
		
		assertThat(computerDtos.size(), is(2));
		assertThat(computerDtos.get(0).getIntroduced(), nullValue());
		assertThat(computerDtos.get(0).getDiscontinued(), is("2000-10-10 10:10:10"));
		assertThat(computerDtos.get(1).getIntroduced(), nullValue());
		
		ComputerDto computerDto = DtoMapper.INSTANCE.mapComputer(computer);
		assertThat(computerDtos.get(0), is(computerDto));
	}
}
