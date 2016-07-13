package com.dao;

import java.sql.Connection;
import java.util.List;

import com.beans.Thesis;

public class ThesisDao implements IDao<Thesis> {

	@Override
	public void create(Thesis bean, Connection connection) 
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id, Connection connection) 
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean update(Thesis bean, Connection connection) 
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Thesis get(int id, Connection connection) 
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Thesis> get(List<Integer> id, Connection connection) 
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
