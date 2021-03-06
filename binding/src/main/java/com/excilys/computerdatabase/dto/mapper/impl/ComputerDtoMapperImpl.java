package com.excilys.computerdatabase.dto.mapper.impl;

import com.excilys.computerdatabase.dto.mapper.CompanyDtoMapper;
import com.excilys.computerdatabase.dto.mapper.ComputerDtoMapper;
import com.excilys.computerdatabase.dto.model.CompanyDto;
import com.excilys.computerdatabase.dto.model.ComputerDto;
import com.excilys.computerdatabase.mapper.impl.DateMapper;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Vincent Galloy
 *         The Class ComputerDtoMapperImpl.
 */
@Service
public class ComputerDtoMapperImpl implements ComputerDtoMapper {
    @Autowired
    private CompanyDtoMapper companyDtoMapper;

    @Override
    public ComputerDto mapFromModel(Computer model) {
        ComputerDto computerDto = new ComputerDto();
        computerDto.setName(model.getName());
        if (model.getId() != null) {
            computerDto.setId(model.getId().toString());
        } else {
            computerDto.setId(null);
        }
        if (model.getCompany() != null) {
            computerDto.setCompanyName(model.getCompany().getName());
            Long id = model.getCompany().getId();
            if(id != null) {
                computerDto.setCompanyId(id.toString());
            }
        } else {
            computerDto.setCompanyName(null);
        }
        computerDto.setName(model.getName());
        if (model.getDiscontinued() != null) {
            computerDto.setDiscontinued(DateMapper.convertIntoString(model.getDiscontinued()));
        } else {
            computerDto.setDiscontinued(null);
        }
        if (model.getIntroduced() != null) {
            computerDto.setIntroduced(DateMapper.convertIntoString(model.getIntroduced()));
        } else {
            computerDto.setIntroduced(null);
        }
        return computerDto;
    }

    @Override
    public Computer mapToModel(ComputerDto dto) {
        Computer computer = new Computer();
        Company company = companyDtoMapper.mapToModel(new CompanyDto(dto.getCompanyId(), dto.getCompanyName()));

        computer.setCompany(company);
        computer.setName(dto.getName());
        computer.setIntroduced(DateMapper.extractFromString(dto.getIntroduced()));
        computer.setDiscontinued(DateMapper.extractFromString(dto.getDiscontinued()));
        computer.setId(getId(dto.getId()));

        return computer;
    }
}
