package com.dao;

import java.sql.Connection;
import java.util.List;

import com.beans.Adviser;

public class AdviserDao implements IDao<Adviser> {

	@Override
	public void save(Adviser bean, Connection connection) 
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id, Connection connection) 
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean update(Adviser bean, Connection connection) 
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Adviser get(int id, Connection connection) 
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Adviser> get(List<Integer> id, Connection connection) 
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
