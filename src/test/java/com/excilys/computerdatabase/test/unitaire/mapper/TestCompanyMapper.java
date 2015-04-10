/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.test.unitaire.mapper;

import static org.junit.Assert.assertEquals;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.computerdatabase.dto.model.CompanyDto;
import com.excilys.computerdatabase.mapper.CompanyMapper;
import com.excilys.computerdatabase.model.Company;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/test-application-context.xml" })
public class TestCompanyMapper {

	@Autowired
	CompanyMapper companyMapper;
	
	DateTimeFormatter dateTimeFormatter = DateTimeFormatter
			.ofPattern("yyyy-MM-dd HH:mm:ss");

	@Test
	public void testMapperModelToDtoOk() {
		Company company = new Company(new Long(4), "Company test");
		CompanyDto computerDto = companyMapper.mapModelToDto(company);
		assertEquals(computerDto.getId(), "4");
		assertEquals(computerDto.getName(), "Company test");
	}

	@Test
	public void testMapperListModelToDtoOk() {
		Company company = new Company(new Long(4), "Company test");
		List<Company> companies = new ArrayList<Company>();
		companies.add(company);
		companies.add(new Company());

		List<CompanyDto> companyDtos = companyMapper
				.mapListModelToDto(companies);

		assertEquals(companyDtos.size(), 2);
		assertEquals(companyDtos.get(1).getName(), null);

		CompanyDto companyDto = companyMapper.mapModelToDto(company);
		assertEquals(companyDtos.get(0), companyDto);
	}

	@Test(expected = NullPointerException.class)
	public void testMapperDaoListWrong() {
		companyMapper.mapListModelToDto(null);
	}
	
	@Test
	public void testMapperDtoToModelOk(){
		Company company2 = new Company(new Long(4), "Company test");
		Company company = companyMapper.mapDtoToModel(new CompanyDto("4", "Company test"));
		assertEquals(company.hashCode(), company2.hashCode());
		assertEquals(company, company2);
	}

}
