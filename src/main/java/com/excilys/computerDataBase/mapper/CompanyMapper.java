/**
 * @Author Vincent Galloy
 */
package com.excilys.computerDataBase.mapper;

import java.sql.ResultSet;
import java.util.List;
import java.util.stream.Collectors;

import com.excilys.computerDataBase.dto.CompanyDto;
import com.excilys.computerDataBase.exception.ParsingException;
import com.excilys.computerDataBase.model.Company;

public class CompanyMapper {

	private static final String PARAM_ID = "id";
	private static final String PARAM_NAME = "name";

	public static CompanyDto mapModelToDto(Company company) {
		CompanyDto companyDto = new CompanyDto();
		companyDto.setName(company.getName());
		if (company.getId() != null) {
			companyDto.setId(company.getId().toString());
		} else {
			companyDto.setId(null);
		}
		return companyDto;
	}

	public static Company mapDtoToModel(CompanyDto companyDto) {
		Company company = new Company();

		company.setName(companyDto.getName());
		company.setId(getId(companyDto.getId()));

		return company;
	}

	public static List<CompanyDto> mapListModelToDto(List<Company> companies) {
		return companies.stream().map(e -> mapModelToDto(e))
				.collect(Collectors.toList());
	}

	public static List<Company> mapListDtoToModel(List<CompanyDto> companyDtos) {
		return companyDtos.stream().map(e -> mapDtoToModel(e))
				.collect(Collectors.toList());
	}

	public static Company mapResultSetToModel(ResultSet resultSet) {
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
