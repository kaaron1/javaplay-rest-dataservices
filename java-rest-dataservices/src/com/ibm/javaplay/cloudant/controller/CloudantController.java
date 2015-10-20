package com.ibm.javaplay.cloudant.controller;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ibm.javaplay.cloudant.service.CloudantService;

@Path("/cloudant")
public class CloudantController
{
	private final CloudantService cloudantService;
	
	public CloudantController()
	{
		cloudantService = new CloudantService();
			
	}

	@GET
	@Path("/test")
    public String test
    (
    )
    {
		return cloudantService.test();
		
	}
	
	@GET
	@Path("/getDatabases")
	@Produces(MediaType.APPLICATION_JSON)
    public List<String> getDatabases
    (
    )
    {
		return cloudantService.getDatabases();

	}
	
	@POST
	@Path("/createDatabase")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> createDatabase
    (
    		Map<String, Object> payload
    )
    {
		return cloudantService.createDatabase(payload);
		
	}
	
	@POST
	@Path("/createDocument")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> createDocument
    (
    		Map<String, Object> payload
    )
    {
		return cloudantService.createDocument(payload);
		
	}
	
	@POST
	@Path("/getDocument")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> post
    (
    		Map<String, Object> payload
    )
    {
		return cloudantService.getDocument(payload);
		
	}
	
}

