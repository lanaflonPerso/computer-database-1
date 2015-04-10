/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.mapper.impl;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.computerdatabase.dto.model.CompanyDto;
import com.excilys.computerdatabase.dto.model.ComputerDto;
import com.excilys.computerdatabase.exception.ParsingException;
import com.excilys.computerdatabase.mapper.CompanyMapper;
import com.excilys.computerdatabase.mapper.ComputerMapper;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;

@Service
public class ComputerMapperImpl implements ComputerMapper{

	private static final String COMPA_NAME = "compa.name";
	private static final String PARAM_ID = "id";
	private static final String PARAM_NAME = "name";
	private static final String PARAM_INTRODUCED = "introduced";
	private static final String PARAM_DISCONTINUED = "discontinued";
	private static final String PARAM_COMPANY_ID = "company_id";

	@Autowired
	private CompanyMapper companyMapper;
	
	@Override
	public ComputerDto mapModelToDto(Computer computer) {
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
			computerDto.setDiscontinued(DateMapper.convertIntoString(computer.getDiscontinued()));
		} else {
			computerDto.setDiscontinued(null);
		}
		if (computer.getIntroduced() != null) {
			computerDto.setIntroduced(DateMapper.convertIntoString(computer.getIntroduced()));
		} else {
			computerDto.setIntroduced(null);
		}
		return computerDto;
	}

	@Override
	public Computer mapDtoToModel(ComputerDto computerDto) {
		Computer computer = new Computer();
		Company company = companyMapper.mapDtoToModel(new CompanyDto(
				computerDto.getCompanyId(), computerDto.getCompanyName()));

		computer.setCompany(company);
		computer.setName(computerDto.getName());
		computer.setIntroduced(DateMapper.exctractFromString(computerDto.getIntroduced()));
		computer.setDiscontinued(DateMapper.exctractFromString(computerDto.getDiscontinued()));
		computer.setId(getId(computerDto.getId()));

		return computer;
	}
	
	public Computer mapResultSetToModel(ResultSet resultSet) {
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

	private static Long getId(String id) {
		if (id == null) {
			return null;
		} else {
			return Long.valueOf(id);
		}
	}

}
