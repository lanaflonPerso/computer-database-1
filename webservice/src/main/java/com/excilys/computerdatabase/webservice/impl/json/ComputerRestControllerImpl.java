package com.excilys.computerdatabase.webservice.impl.json;

import com.excilys.computerdatabase.dto.mapper.ComputerDtoMapper;
import com.excilys.computerdatabase.dto.model.ComputerDto;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.services.ComputerService;
import com.excilys.computerdatabase.sort.SortCriteria;
import com.excilys.computerdatabase.webservice.ComputerResource;
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
 *         The Class ComputerRestController.
 */
@RestController
@RequestMapping("rest/json/computer")
public class ComputerRestControllerImpl implements ComputerResource {
    @Autowired
    private ComputerService computerService;
    @Autowired
    private ComputerDtoMapper computerDtoMapper;

    @Override
    @RequestMapping(value = "getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public List<ComputerDto> getAll() {
        return computerDtoMapper.mapListFromModel(computerService.list(new SortCriteria()));
    }

    @Override
    @RequestMapping(value = "getById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public ComputerDto getById(@PathVariable("id") Long id) {
        return computerDtoMapper.mapFromModel(computerService.getById(id));
    }

    @Override
    @RequestMapping(value = "create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
    public ComputerDto create(@RequestBody ComputerDto t) {
        Computer computer = computerDtoMapper.mapToModel(t);
        computerService.create(computer);
        return computerDtoMapper.mapFromModel(computer);
    }

    @Override
    @RequestMapping(value = "update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
    public ComputerDto update(@RequestBody ComputerDto t) {
        Computer computer = computerDtoMapper.mapToModel(t);
        computerService.update(computer);
        return computerDtoMapper.mapFromModel(computer);
    }

    @Override
    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public Response delete(@PathVariable("id") Long id) {
        computerService.delete(id);
        return Response.ok("ok").build();
    }
}