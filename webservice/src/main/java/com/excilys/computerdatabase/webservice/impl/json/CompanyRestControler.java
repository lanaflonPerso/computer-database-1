/**
 * @Author Vincent Galloy
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

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.service.services.CompanyService;
import com.excilys.computerdatabase.sort.SortCriteria;
import com.excilys.computerdatabase.webservice.CompanyRessource;


/**
 * The Class CompanyRestControler.
 */
@RestController
@RequestMapping("rest/json/company")
public class CompanyRestControler implements CompanyRessource {
	
	/** The company service. */
	@Autowired
	private CompanyService companyService;
	
	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.webservice.CommonRessource#getAll()
	 */
	@Override
	@GET
	@RequestMapping("getAll")
	public List<Company> getAll() {
		return companyService.list(new SortCriteria());
	}
	
	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.webservice.CommonRessource#getById(java.lang.Long)
	 */
	@Override
	@GET
	@RequestMapping("getById/{id}")
	public Company getById(@PathParam("id") Long id) {
		return companyService.getById(id);
	}
	
	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.webservice.CommonRessource#create(java.lang.Object)
	 */
	@Override
	@POST
	@RequestMapping("create")
	public Company create(Company t) {
		companyService.create(t);
		return t;
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.webservice.CommonRessource#update(java.lang.Object)
	 */
	@Override
	@POST
	@RequestMapping("update")
	public Company update(Company t) {
		companyService.update(t);
		return t;
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.webservice.CommonRessource#delete(java.lang.Long)
	 */
	@Override
	@DELETE
	@Produces(MediaType.TEXT_HTML)
	@RequestMapping("delete/{id}")
	public Response delete(@PathParam("id") Long id) {
		companyService.delete(id);
		return Response.ok("ok").build();
	}
    
}