package com.excilys.computerdatabase.webservice.impl.xml;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.services.ComputerService;
import com.excilys.computerdatabase.sort.SortCriteria;
import com.excilys.computerdatabase.webservice.ComputerRessource;

@Path("/computer")
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
public class ComputerRessourceImpl implements ComputerRessource {
	@Autowired
	private ComputerService computerService;
	
	@Override
	@GET
	@Path("getAll")
	public List<Computer> getAll() {
		return computerService.list(new SortCriteria());
	}
	
	@Override
	@GET
	@Path("getById/{id}")
	public Computer getById(@PathParam("id") Long id) {
		return computerService.getById(id);
	}
	
	@Override
	@POST
	@Path("create")
	public Computer create(Computer t) {
		computerService.create(t);
		return t;
	}

	@Override
	@POST
	@Path("update")
	public Computer update(Computer t) {
		computerService.update(t);
		return t;
	}

	@Override
	@DELETE
	@Produces(MediaType.TEXT_HTML)
	@Path("delete/{id}")
	public Response delete(@PathParam("id") Long id) {
		computerService.delete(id);
		return Response.ok("ok").build();
	}
}