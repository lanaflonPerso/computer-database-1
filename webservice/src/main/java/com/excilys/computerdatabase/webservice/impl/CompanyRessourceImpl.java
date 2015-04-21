package com.excilys.computerdatabase.webservice.impl;

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

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.service.CompanyService;
import com.excilys.computerdatabase.sort.SortCriteria;
import com.excilys.computerdatabase.webservice.CompanyRessource;

@Path("/company")
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
public class CompanyRessourceImpl implements CompanyRessource {
	@Autowired
	private CompanyService companyService;
	
	@Override
	@GET
	@Path("getAll")
	public List<Company> getAll() {
		return companyService.list(new SortCriteria());
	}
	
	@Override
	@GET
	@Path("getById/{id}")
	public Company getById(@PathParam("id") Long id) {
		return companyService.getById(id);
	}
	
	@Override
	@POST
	@Path("create")
	public Company create(Company t) {
		companyService.create(t);
		return t;
	}

	@Override
	@POST
	@Path("update")
	public Company update(Company t) {
		companyService.update(t);
		return t;
	}

	@Override
	@DELETE
	@Produces(MediaType.TEXT_HTML)
	@Path("delete/{id}")
	public Response delete(@PathParam("id") Long id) {
		companyService.delete(id);
		return Response.ok("ok").build();
	}
}
