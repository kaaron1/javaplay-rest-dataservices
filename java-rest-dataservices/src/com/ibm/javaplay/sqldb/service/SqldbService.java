package com.ibm.javaplay.sqldb.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.ibm.javaplay.ServiceDiscovery;

public class SqldbService
{
	private final QueryRunner queryRunner;
	
	public SqldbService()
	{
		queryRunner =
				new QueryRunner
				(
						ServiceDiscovery.getInstance().getSqldbDataSource()
				);
			
	}

    public String insert
    (
    		Integer id,
    		String name
    )
    {
		// verify query parameters present
		
		if (id == null)
		{
			return "Parameter \"id\" is required.";
			
		}
		
		if (name == null)
		{
			return "Parameter \"name\" is required.";
			
		}
		
		// build output
		
		String output;
		
		try
		{
			Number existingRecordCount =
					queryRunner.query
					(
							"select count(*) from \"MYTABLE\" where \"ID\" = ?",
							new ScalarHandler<Number>(),
							new Object[]{id, }
					);
			
			if (existingRecordCount.longValue() == 0)
			{
				int result =
						queryRunner.update
						(
								"insert into \"MYTABLE\" (\"ID\", \"NAME\") values (?, ?)",
								new Object[]{id, name, }
						);
				
				output = "Successfully inserted " + result + " rows.";
				
			}
			else
			{
				output = "Record with this ID already exists.";
				
			}
			
		}
		catch (SQLException e)
		{
			throw new RuntimeException(e);

		}
		
		return output;

	}
	
    public String update
    (
    		Integer id,
    		String name
    )
    {
		// verify query parameters present
		
		if (id == null)
		{
			return "Parameter \"id\" is required.";
			
		}
		
		if (name == null)
		{
			return "Parameter \"name\" is required.";
			
		}
		
		// build output
		
		String output;
		
		try
		{
			Number existingRowCount =
					queryRunner.query
					(
							"select count(*) from \"MYTABLE\" where \"ID\" = ?",
							new ScalarHandler<Number>(),
							new Object[]{id, }
					);
			
			if (existingRowCount.longValue() == 0)
			{
				output = "Record with this ID does not exists.";
				
			}
			else
			{
				int result =
						queryRunner.update
						(
								"update \"MYTABLE\" set \"NAME\" = ? where \"ID\" = ?",
								new Object[]{name, id, }
						);
				
				output = "Successfully updated " + result + " rows.";
				
			}
			
		}
		catch (SQLException e)
		{
			throw new RuntimeException(e);
			
		}
		
		return output;

	}
	
    public String delete
    (
    		Integer id
    )
    {
		// verify query parameters present
		
		if (id == null)
		{
			return "Parameter \"id\" is required.";
			
		}
		
		// build output
		
		int result;
		
		try
		{
			result = queryRunner.update
			(
					"delete from \"MYTABLE\" where \"ID\" = ?",
					new Object[]{id, }
			);
		}
		catch (SQLException e)
		{
			throw new RuntimeException(e);
			
		}
		
		return "Successfully deleted " + result + " rows.";

	}
	
    public List<Map<String, Object>> select
    (
    		Integer id
    )
    {
		List<Map<String, Object>> output;

		try
		{
			if (id == null)
			{
				output =
						queryRunner.query
						(
								"select * from \"MYTABLE\"",
								new MapListHandler()
						);
				
			}
			else
			{
				output =
						queryRunner.query
						(
								"select * from \"MYTABLE\" where \"ID\" = ?",
								new MapListHandler(),
								new Object[]{id, }
						);
				
			}
			
		}
		catch (SQLException e)
		{
			throw new RuntimeException(e);
			
		}
		
		return output;

	}
	
}

