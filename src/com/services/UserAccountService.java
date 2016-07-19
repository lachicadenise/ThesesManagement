package com.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.beans.UserAccount;
import com.dao.UserAccountDao;
import com.utils.MySQLConnectionFactory;

@Path("/userAccounts")
public class UserAccountService {
	
	@GET
	@Path("/count")
	@Produces(MediaType.APPLICATION_JSON)
	public Response count(@QueryParam("searchValue") String searchValue){
		Response response = null;
		Connection connection = null;
		try{
			connection = MySQLConnectionFactory.createConnection();
			UserAccountDao dao = new UserAccountDao();
			int count = dao.count(searchValue, connection);
			JSONObject json = new JSONObject();
			json.put("count", count);
			response = Response.ok(json).build();
		}catch(Exception e){
			response = Response.serverError().build();
		} finally{
			if(connection != null){
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					//ignore
				}
			}
		}
		return response;
	}
	
	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(UserAccount userAccount){
		Response response = null;
		if(userAccount == null){
			response = Response.serverError().entity("UserAccount cannot be null").build();
		} else {
			Connection connection = null;
			try {
				connection = MySQLConnectionFactory.createConnection();
				UserAccountDao dao = new UserAccountDao();
				if(dao.usernameExists(userAccount.getUsername(), connection)){
					response = Response.serverError().entity("Username already exists").build();
				}else{
					dao.create(userAccount, connection);
					connection.commit();
					response = Response.ok(userAccount).build();					
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				response = Response.serverError().entity(e.getMessage()).build();
			}finally{
				if(connection != null){
					try {
						connection.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						response = Response.serverError().entity(e.getMessage()).build();
					}
				}
			}
		}
		return response;
	}
	
	@GET
	@Path("/usernameExists")
	@Produces(MediaType.APPLICATION_JSON)
	public Response usernameExists(@DefaultValue("") @QueryParam("username") String username){
		boolean usernameExists = false;
		Connection connection = null;
		try {
			connection = MySQLConnectionFactory.createConnection();
			UserAccountDao dao = new UserAccountDao();
			usernameExists = dao.usernameExists(username, connection);
		} catch (ClassNotFoundException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(connection != null){
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		JSONObject result = new JSONObject();
		try {
			result.put("usernameExists", usernameExists);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.ok(result).build();
	}
	
	@GET
	@Path("/get")
	@Produces({MediaType.APPLICATION_JSON})
	public Response get(
			@DefaultValue("") @QueryParam("searchValue") String searchValue, 
			@DefaultValue("30") @QueryParam("limit") int limit,
			@DefaultValue("0") @QueryParam("from") int from
			){
		List<UserAccount> userAccounts = new ArrayList<UserAccount>();
//		try{
//			UserAccountDao dao = new UserAccountDao();
//			userAccounts = dao.get(searchValue, from, limit, null);
//		} catch(Exception e){
//			e.printStackTrace();
//		}
		return Response.ok(userAccounts).build();
	}
	
//	@GET
//	@Path("/Get/{id}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response get(@PathParam("id") int id){
//		UserAccount userAccount = null;
//		try{
//			UserAccountDao dao = new UserAccountDao();
//			userAccount = dao.get(id, null);
//		} catch(Exception e){
//			e.printStackTrace();
//		}
//		return Response.
//				status(userAccount == null ? 204 : 200).
//				entity(userAccount).
//				build();
//	}
	
}
