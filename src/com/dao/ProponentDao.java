package com.dao;

import java.sql.Connection;
import java.util.List;

import com.beans.Proponent;

public class ProponentDao implements IDao<Proponent>{

	@Override
	public void save(Proponent bean, Connection connection) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id, Connection connection) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean update(Proponent bean, Connection connection) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Proponent get(int id, Connection connection) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Proponent> get(List<Integer> id, Connection connection) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
