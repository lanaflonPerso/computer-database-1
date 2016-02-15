package com.excilys.computerdatabase.dto.mapper.impl;

import com.excilys.computerdatabase.dto.mapper.CompanyDtoMapper;
import com.excilys.computerdatabase.dto.model.CompanyDto;
import com.excilys.computerdatabase.model.Company;
import org.springframework.stereotype.Service;

/**
 * @author Vincent Galloy
 *         The Class CompanyDtoMapperImpl.
 */
@Service
public class CompanyDtoMapperImpl implements CompanyDtoMapper {

    @Override
    public CompanyDto mapFromModel(Company model) {
        CompanyDto companyDto = new CompanyDto();
        companyDto.setName(model.getName());
        if (model.getId() != null) {
            companyDto.setId(model.getId().toString());
        } else {
            companyDto.setId(null);
        }
        return companyDto;
    }

    @Override
    public Company mapToModel(CompanyDto dto) {
        Company company = new Company();

        company.setName(dto.getName());
        company.setId(getId(dto.getId()));

        return company;
    }
}
