package com.ibm.javaplay;

import javax.sql.DataSource;

import org.json.JSONException;
import org.json.JSONObject;

import com.cloudant.client.api.CloudantClient;
import com.ibm.db2.jcc.DB2SimpleDataSource;

public class ServiceDiscovery
{
	private static ServiceDiscovery serviceDiscovery = new ServiceDiscovery();
	
	JSONObject vcapServices;
	
	private ServiceDiscovery()
	{
		// parse VCAP_SERVICES
		
		try
		{
			vcapServices = new JSONObject(System.getenv("VCAP_SERVICES"));
			
		}
		catch (JSONException e)
		{
			throw new RuntimeException(e);
			
		}
			
	}
	
	public static ServiceDiscovery getInstance()
	{
		return serviceDiscovery;
		
	}
	
	public DataSource getSqldbDataSource()
	{
		String hostName;
		int portNumber;
		String databaseName;
		String username;
		String password;
		
		// parse VCAP_SERVICES
		
		try
		{
			JSONObject service = vcapServices.getJSONArray("sqldb").getJSONObject(0);
			JSONObject credentials = service.getJSONObject("credentials");
			hostName = credentials.getString("hostname");
			portNumber = credentials.getInt("port");
			databaseName = credentials.getString("db");
			username = credentials.getString("username");
			password = credentials.getString("password");
			
		}
		catch (JSONException e)
		{
			throw new RuntimeException(e);
			
		}
		
		DB2SimpleDataSource dataSource = new DB2SimpleDataSource();
		dataSource.setDriverType(4);
		dataSource.setServerName(hostName);
		dataSource.setPortNumber(portNumber);
		dataSource.setDatabaseName(databaseName); 
		dataSource.setUser(username);
		dataSource.setPassword(password);
		
		return dataSource;

	}

	public CloudantClient getCoudantClient()
	{
		String host;
		String username;
		String password;
		
		// parse VCAP_SERVICES
		
		try
		{
			JSONObject service = vcapServices.getJSONArray("cloudantNoSQLDB").getJSONObject(0);
			JSONObject credentials = service.getJSONObject("credentials");
			host = credentials.getString("host");
			username = credentials.getString("username");
			password = credentials.getString("password");
			
		}
		catch (JSONException e)
		{
			throw new RuntimeException(e);
			
		}
		
		String account = "https://" + host;

		CloudantClient cloudantClient = new CloudantClient(account, username, password);
		
		return cloudantClient;

	}

}
