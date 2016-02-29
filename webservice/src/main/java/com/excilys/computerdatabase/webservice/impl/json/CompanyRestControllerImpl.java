package com.excilys.computerdatabase.webservice.impl.json;

import com.excilys.computerdatabase.dto.mapper.CompanyDtoMapper;
import com.excilys.computerdatabase.dto.model.CompanyDto;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.service.services.CompanyService;
import com.excilys.computerdatabase.sort.SortCriteria;
import com.excilys.computerdatabase.webservice.CompanyResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author Vincent Galloy
 *         The Class CompanyRestControler.
 */
@RestController
@RequestMapping("rest/json/company")
public class CompanyRestControllerImpl implements CompanyResource {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CompanyDtoMapper companyDtoMapper;

    @Override
    @RequestMapping(value = "getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public List<CompanyDto> getAll() {
        return companyDtoMapper.mapListFromModel(companyService.list(new SortCriteria()));
    }

    @Override
    @RequestMapping(value = "getById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public CompanyDto getById(@PathVariable("id") Long id) {
        return companyDtoMapper.mapFromModel(companyService.getById(id));
    }

    @Override
    @RequestMapping(value = "create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
    public CompanyDto create(@RequestBody CompanyDto t) {
        Company company = companyDtoMapper.mapToModel(t);
        companyService.create(company);
        return companyDtoMapper.mapFromModel(company);
    }

    @Override
    @RequestMapping(value = "update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
    public CompanyDto update(@RequestBody CompanyDto t) {
        Company company = companyDtoMapper.mapToModel(t);
        companyService.update(company);
        return companyDtoMapper.mapFromModel(company);
    }

    @Override
    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE, produces = MediaType.TEXT_HTML)
    public Response delete(@PathVariable("id") Long id) {
        companyService.delete(id);
        return Response.ok("ok").build();
    }
}