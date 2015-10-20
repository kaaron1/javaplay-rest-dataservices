package com.ibm.javaplay.cloudant.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import com.cloudant.client.api.model.Response;
import com.ibm.javaplay.ServiceDiscovery;

public class CloudantService
{
	private final CloudantClient cloudantClient;
	
	public CloudantService()
	{
		cloudantClient = ServiceDiscovery.getInstance().getCoudantClient();
		
	}
	
	public String test()
	{
		cloudantClient.createDB("javaplay");
		
		return Arrays.toString(cloudantClient.getAllDbs().toArray());

	}

    public List<String> getDatabases
    (
    )
    {
    	return cloudantClient.getAllDbs();

	}
	
	public Map<String, Object> createDatabase
	(
			Map<String, Object> payload
	)
	{
		Map<String, Object> output = new HashMap<String, Object>();
		
		if (!payload.containsKey("databaseName"))
		{
			output.put("error", "Parameter \"databaseName\" is required.");
			
			return output;
			
		}
		
		String databaseName = (String) payload.get("databaseName");
		
		if (cloudantClient.getAllDbs().contains(databaseName))
		{
			output.put("error", "Database \"" + databaseName + "\" already exists.");
			
		}
		else
		{
			cloudantClient.createDB(databaseName);
			
			output.put("message", "Database \"" + databaseName + "\" created successfully..");
			
		}
		
		return output;

	}

	public Map<String, Object> createDocument
	(
			Map<String, Object> payload
	)
	{
		Map<String, Object> output = new HashMap<String, Object>();
		
		if (!payload.containsKey("databaseName"))
		{
			output.put("error", "Parameter \"databaseName\" is required.");
			
			return output;
			
		}
		
		if (!payload.containsKey("object"))
		{
			output.put("error", "Parameter \"object\" is required.");
			
			return output;
			
		}
		
		if (!(payload.get("object") instanceof Map<?, ?>))
		{
			output.put("error", "Parameter \"object\" should be of type Map.");
			
			return output;
			
		}
		
		String databaseName = (String) payload.get("databaseName");
		@SuppressWarnings("unchecked")
		Map<String, Object> object = (Map<String, Object>) payload.get("object");
		
		if (!cloudantClient.getAllDbs().contains(databaseName))
		{
			output.put("error", "Database \"" + databaseName + "\" does not exist.");
			
		}
		else
		{
			Database database = cloudantClient.database(databaseName, false);
			
			Response response = database.post(object);
			
			output.put("response", response.toString());
			
		}
		
		return output;

	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getDocument
	(
			Map<String, Object> payload
	)
	{
		Map<String, Object> output = new HashMap<String, Object>();
		
		if (!payload.containsKey("databaseName"))
		{
			output.put("error", "Parameter \"databaseName\" is required.");
			
			return output;
			
		}
		
		if (!payload.containsKey("id"))
		{
			output.put("error", "Parameter \"id\" is required.");
			
			return output;
			
		}
		
		String databaseName = (String) payload.get("databaseName");
		String id = (String) payload.get("id");
		
		if (!cloudantClient.getAllDbs().contains(databaseName))
		{
			output.put("error", "Database \"" + databaseName + "\" does not exist.");
			
		}
		else
		{
			Database database = cloudantClient.database(databaseName, false);
			
			output.putAll((Map<String, Object>) database.find(Map.class, id));
			
		}
		
		return output;

	}

}

