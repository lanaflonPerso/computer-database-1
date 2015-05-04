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

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.services.ComputerService;
import com.excilys.computerdatabase.sort.SortCriteria;
import com.excilys.computerdatabase.webservice.ComputerRessource;


/**
 * The Class ComputerRestControler.
 */
@RestController
@RequestMapping("rest/json/computer")
public class ComputerRestControler implements ComputerRessource {
	
	/** The Computer service. */
	@Autowired
	private ComputerService ComputerService;
	
	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.webservice.CommonRessource#getAll()
	 */
	@Override
	@GET
	@RequestMapping("getAll")
	public List<Computer> getAll() {
		return ComputerService.list(new SortCriteria());
	}
	
	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.webservice.CommonRessource#getById(java.lang.Long)
	 */
	@Override
	@GET
	@RequestMapping("getById/{id}")
	public Computer getById(@PathParam("id") Long id) {
		return ComputerService.getById(id);
	}
	
	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.webservice.CommonRessource#create(java.lang.Object)
	 */
	@Override
	@POST
	@RequestMapping("create")
	public Computer create(Computer t) {
		ComputerService.create(t);
		return t;
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.webservice.CommonRessource#update(java.lang.Object)
	 */
	@Override
	@POST
	@RequestMapping("update")
	public Computer update(Computer t) {
		ComputerService.update(t);
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
		ComputerService.delete(id);
		return Response.ok("ok").build();
	}
    
}