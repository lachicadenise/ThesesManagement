package com.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.beans.UserAccount;

public class UserAccountDao implements IDao<UserAccount> {

	@Override
	public void save(UserAccount bean, Connection connection) 
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id, Connection connection) 
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean update(UserAccount bean, Connection connection) 
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UserAccount get(int id, Connection connection) 
			throws Exception {
		// TODO Auto-generated method stub
		UserAccount userAccount = new UserAccount(1, "joshuazabala", "password01", "Zabala", "Joshua", "Perez", false);
		return userAccount;
	}

	@Override
	public List<UserAccount> get(List<Integer> id, Connection connection) 
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<UserAccount> get(String value, int from, int limit, Connection connection) 
			throws Exception {
		List<UserAccount> userAccounts = new ArrayList<UserAccount>();
		for(int a = 0; a < 10; a++){
			userAccounts.add(new UserAccount(a + 1, "joshuazabala" + a, "password01", "Zabala" + a, "Joshua" + a, "Perez" + a, false));
		}
		return userAccounts;
	}

}
