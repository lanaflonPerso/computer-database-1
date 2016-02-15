package com.excilys.computerdatabase.mapper;

import com.excilys.computerdatabase.dto.mapper.CompanyDtoMapper;
import com.excilys.computerdatabase.dto.model.CompanyDto;
import com.excilys.computerdatabase.model.Company;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Vincent Galloy
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/test-binding-context.xml"})
public class TestCompanyMapper {
    @Autowired
    private CompanyDtoMapper companyDtoMapper;

    @Test
    public void testMapperModelToDtoOk() {
        Company company = new Company(new Long(4), "Company test");
        CompanyDto computerDto = companyDtoMapper.mapFromModel(company);
        assertEquals(computerDto.getId(), "4");
        assertEquals(computerDto.getName(), "Company test");
    }

    @Test
    public void testMapperModelToDtoNullAttributs() {
        Company company = new Company(null, null);
        CompanyDto computerDto = companyDtoMapper.mapFromModel(company);
        assertEquals(null, computerDto.getId());
        assertEquals(null, computerDto.getName());
    }

    @Test(expected = NullPointerException.class)
    public void testMapperModelToDtoNullCompany() {
        companyDtoMapper.mapFromModel(null);
    }

    @Test
    public void testMapperListModelToDtoOk() {
        Company company = new Company(new Long(4), "Company test");
        List<Company> companies = new ArrayList<Company>();
        companies.add(company);
        companies.add(new Company());

        List<CompanyDto> companyDtos = companyDtoMapper.mapListFromModel(companies);

        assertEquals(companyDtos.size(), 2);
        assertEquals(companyDtos.get(1).getName(), null);

        CompanyDto companyDto = companyDtoMapper.mapFromModel(company);
        assertEquals(companyDtos.get(0), companyDto);
    }

    @Test(expected = NullPointerException.class)
    public void testMapperDaoListWrong() {
        companyDtoMapper.mapListFromModel(null);
    }

    @Test
    public void testMapperDtoToModelOk() {
        Company company2 = new Company(new Long(4), "Company test");
        Company company = companyDtoMapper.mapToModel(new CompanyDto("4", "Company test"));
        assertEquals(company.hashCode(), company2.hashCode());
        assertEquals(company, company2);
    }

}
