package com.ibm.javaplay.sqldb.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.ibm.javaplay.sqldb.service.SqldbService;

@Path("/sqldb")
public class SqldbController
{
	private final SqldbService sqldbService;
	
	public SqldbController()
	{
		sqldbService = new SqldbService();
			
	}

	@GET
	@Path("/insert")
    public String insert
    (
    		@QueryParam("id") Integer id,
    		@QueryParam("name") String name
    )
    {
		return sqldbService.insert(id, name);

	}
	
	@GET
	@Path("/update")
    public String update
    (
    		@QueryParam("id") Integer id,
    		@QueryParam("name") String name
    ) throws SQLException
    {
		return sqldbService.update(id, name);

	}
	
	@GET
	@Path("/delete")
    public String delete
    (
    		@QueryParam("id") Integer id
    ) throws SQLException
    {
		return sqldbService.delete(id);

	}
	
	@GET
	@Path("/select")
	@Produces("application/json")
    public List<Map<String, Object>> select
    (
    		@QueryParam(value = "id") Integer id
    ) throws SQLException
    {
		return sqldbService.select(id);

	}
	
}

