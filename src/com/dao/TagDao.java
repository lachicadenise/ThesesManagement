package com.dao;

import java.sql.Connection;
import java.util.List;

import com.beans.Tag;

public class TagDao implements IDao<Tag>{

	@Override
	public void save(Tag bean, Connection connection) 
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id, Connection connection) 
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean update(Tag bean, Connection connection) 
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Tag get(int id, Connection connection) 
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tag> get(List<Integer> id, Connection connection) 
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
