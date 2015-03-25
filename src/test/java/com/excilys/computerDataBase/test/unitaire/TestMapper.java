package com.excilys.computerDataBase.test.unitaire;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.excilys.computerDataBase.dto.ComputerDto;
import com.excilys.computerDataBase.mapper.ComputerMapper;
import com.excilys.computerDataBase.model.Company;
import com.excilys.computerDataBase.model.Computer;

public class TestMapper {

	DateTimeFormatter dateTimeFormatter = DateTimeFormatter
			.ofPattern("yyyy-MM-dd HH:mm:ss");

	@Test
	public void testMapperModelToDtoOk() {
		Computer computer = new Computer();
		ComputerMapper.mapModelToDto(computer);
	}

	@Test
	public void testMapperModelToDtoOk2() {
		Computer computer = new Computer(new Long(0), "name",
				LocalDateTime.parse("2000-10-10 10:10:10", dateTimeFormatter),
				null, new Company(new Long(0), "name"));
		ComputerDto computerDto = ComputerMapper.mapModelToDto(computer);
		assertEquals(computerDto.getIntroduced(), "2000-10-10 10:10:10");
	}

	@Test
	public void testMapperModelToDtoOk3() {
		Computer computer = new Computer(null, "name", null,
				LocalDateTime.parse("2000-10-10 10:10:10", dateTimeFormatter),
				new Company(null, "name"));
		ComputerDto computerDto = ComputerMapper.mapModelToDto(computer);
		assertEquals(computerDto.getIntroduced(), null);
		assertEquals(computerDto.getDiscontinued(), "2000-10-10 10:10:10");
	}

	@Test
	public void testMapperListModelToDtoOk() {
		Computer computer = new Computer(null, "name", null,
				LocalDateTime.parse("2000-10-10 10:10:10", dateTimeFormatter),
				new Company(null, "name"));
		List<Computer> computers = new ArrayList<Computer>();
		computers.add(computer);
		computers.add(new Computer());

		List<ComputerDto> computerDtos = ComputerMapper
				.mapListModelToDto(computers);

		assertEquals(computerDtos.size(), 2);
		assertEquals(computerDtos.get(1).getIntroduced(), null);

		ComputerDto computerDto = ComputerMapper.mapModelToDto(computer);
		assertEquals(computerDtos.get(0), computerDto);
	}

	@Test(expected = NullPointerException.class)
	public void testMapperDaoListWrong() {
			ComputerMapper.mapModelToDto(null);
	}
	
	@Test
	public void testMapperDtoToModelOk(){
		Computer computer2 = new Computer(new Long(5), "name", LocalDateTime.parse("2000-10-10 20:25:26", dateTimeFormatter), null, new Company(new Long(4), null));
		Computer computer = ComputerMapper.mapDtoToModel(new ComputerDto("5", "name", "2000-10-10 20:25:26", "", "4", null));
		assertEquals(computer.hashCode(), computer2.hashCode());
		assertEquals(computer, computer2);
	}

}
