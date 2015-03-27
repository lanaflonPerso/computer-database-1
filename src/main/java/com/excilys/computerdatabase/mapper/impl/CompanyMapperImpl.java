/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.mapper.impl;

import java.sql.ResultSet;

import org.springframework.stereotype.Service;

import com.excilys.computerdatabase.dto.model.CompanyDto;
import com.excilys.computerdatabase.exception.ParsingException;
import com.excilys.computerdatabase.mapper.CompanyMapper;
import com.excilys.computerdatabase.model.Company;
@Service
public class CompanyMapperImpl implements CompanyMapper {

	private static final String PARAM_ID = "id";
	private static final String PARAM_NAME = "name";

	@Override
	public CompanyDto mapModelToDto(Company company) {
		CompanyDto companyDto = new CompanyDto();
		companyDto.setName(company.getName());
		if (company.getId() != null) {
			companyDto.setId(company.getId().toString());
		} else {
			companyDto.setId(null);
		}
		return companyDto;
	}
	
	@Override
	public Company mapDtoToModel(CompanyDto companyDto) {
		Company company = new Company();

		company.setName(companyDto.getName());
		company.setId(getId(companyDto.getId()));

		return company;
	}
	
	@Override
	public Company mapResultSetToModel(ResultSet resultSet) {
		try {
			Long id = resultSet.getLong(PARAM_ID);
			String name = resultSet.getString(PARAM_NAME);
			return new Company(id, name);
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
