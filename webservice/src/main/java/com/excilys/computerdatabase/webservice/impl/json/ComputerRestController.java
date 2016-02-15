/**
 * @author Vincent Galloy
 * 
 */
package com.excilys.computerdatabase.webservice.impl.json;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.services.ComputerService;
import com.excilys.computerdatabase.sort.SortCriteria;
import com.excilys.computerdatabase.webservice.ComputerResource;


/**
 * The Class ComputerRestControler.
 */
@RestController
@RequestMapping("rest/json/computer")
public class ComputerRestController implements ComputerResource {
	
	/** The Computer service. */
	@Autowired
	private ComputerService ComputerService;

	@Override
	@GET
	@RequestMapping("getAll")
	public List<Computer> getAll() {
		return ComputerService.list(new SortCriteria());
	}

	@Override
	@GET
	@RequestMapping("getById/{id}")
	public Computer getById(@PathParam("id") Long id) {
		return ComputerService.getById(id);
	}

	@Override
	@POST
	@RequestMapping("create")
	public Computer create(Computer t) {
		ComputerService.create(t);
		return t;
	}

	@Override
	@POST
	@RequestMapping("update")
	public Computer update(Computer t) {
		ComputerService.update(t);
		return t;
	}

	@Override
	@DELETE
	@Produces(MediaType.TEXT_HTML)
	@RequestMapping("delete/{id}")
	public Response delete(@PathParam("id") Long id) {
		ComputerService.delete(id);
		return Response.ok("ok").build();
	}
    
}