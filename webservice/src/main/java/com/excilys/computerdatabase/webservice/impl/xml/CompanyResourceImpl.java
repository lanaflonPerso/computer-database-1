package com.excilys.computerdatabase.webservice.impl.xml;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.service.services.CompanyService;
import com.excilys.computerdatabase.sort.SortCriteria;
import com.excilys.computerdatabase.webservice.CompanyResource;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author Vincent Galloy
 *         The Class CompanyResourceImpl.
 */
@Path("/company")
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
public class CompanyResourceImpl implements CompanyResource {
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