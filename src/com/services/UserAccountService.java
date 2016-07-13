package com.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.beans.UserAccount;

@Path("/userAccounts")
public class UserAccountService {
	
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
