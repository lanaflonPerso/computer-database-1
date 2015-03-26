/**
 * @Author Vincent Galloy
 */
package com.excilys.computerDataBase.mapper;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import com.excilys.computerDataBase.dto.CompanyDto;
import com.excilys.computerDataBase.dto.ComputerDto;
import com.excilys.computerDataBase.exception.ParsingException;
import com.excilys.computerDataBase.model.Company;
import com.excilys.computerDataBase.model.Computer;
import com.excilys.computerDataBase.validation.Validator;

public class ComputerMapper {

	private static final String COMPA_NAME = "compa.name";
	private static final String PARAM_ID = "id";
	private static final String PARAM_NAME = "name";
	private static final String PARAM_INTRODUCED = "introduced";
	private static final String PARAM_DISCONTINUED = "discontinued";
	private static final String PARAM_COMPANY_ID = "company_id";

	static final private DateTimeFormatter dateTimeFormatter = DateTimeFormatter
			.ofPattern("yyyy-MM-dd HH:mm:ss");

	public static ComputerDto mapModelToDto(Computer computer) {
		ComputerDto computerDto = new ComputerDto();
		computerDto.setName(computer.getName());
		if (computer.getId() != null) {
			computerDto.setId(computer.getId().toString());
		} else {
			computerDto.setId(null);
		}
		if (computer.getCompany() != null) {
			computerDto.setCompanyName(computer.getCompany().getName());
		} else {
			computerDto.setCompanyName(null);
		}
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

	public static Computer mapDtoToModel(ComputerDto computerDto) {
		Computer computer = new Computer();
		Company company = CompanyMapper.mapDtoToModel(new CompanyDto(
				computerDto.getCompanyId(), computerDto.getCompanyName()));

		computer.setCompany(company);
		computer.setName(computerDto.getName());
		computer.setIntroduced(getLocalDateTime(computerDto.getIntroduced()));
		computer.setDiscontinued(getLocalDateTime(computerDto.getDiscontinued()));
		computer.setId(getId(computerDto.getId()));

		return computer;
	}

	public static List<ComputerDto> mapListModelToDto(List<Computer> computers) {
		return computers.stream().map(e -> mapModelToDto(e))
				.collect(Collectors.toList());
	}

	public static List<Computer> mapListDtoToModel(
			List<ComputerDto> computerDtos) {
		return computerDtos.stream().map(e -> mapDtoToModel(e))
				.collect(Collectors.toList());
	}

	public static Computer mapResultSetToModel(ResultSet resultSet) {
		try {
			Long id = resultSet.getLong(PARAM_ID);
			String name = resultSet.getString(PARAM_NAME);
			Timestamp t1 = resultSet.getTimestamp(PARAM_INTRODUCED);
			LocalDateTime introduced = null;
			if (t1 != null) {
				introduced = t1.toLocalDateTime();
			}

			Timestamp t2 = resultSet.getTimestamp(PARAM_DISCONTINUED);
			LocalDateTime discontinued = null;
			if (t2 != null) {
				discontinued = t2.toLocalDateTime();
			}

			Long companyId = resultSet.getLong(PARAM_COMPANY_ID);
			String companyName = resultSet.getString(COMPA_NAME);
			Company company = new Company(companyId, companyName);

			return new Computer(id, name, introduced, discontinued, company);
		} catch (Exception e) {
			throw new ParsingException(ParsingException.CAN_NOT_MAP_RESULT_SET);
		}
	}

	private static LocalDateTime getLocalDateTime(String date) {
		if (date == null) {
			return null;
		} else if ("".equals(date.trim())) {
			return null;
		} else if (Validator.validateDate(date)) {
			return LocalDateTime.parse(date, dateTimeFormatter);
		} else {
			throw new ParsingException(Validator.WRONG_DATE_FORMAT);
		}
	}

	private static Long getId(String id) {
		if (id == null) {
			return null;
		} else {
			return Long.valueOf(id);
		}
	}

}
