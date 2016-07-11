package com.dao;

import java.sql.Connection;
import java.util.List;

public interface IDao<T> {

	public void save(T bean, Connection connection) throws Exception;
	
	public void delete(int id, Connection connection) throws Exception;
	
	public boolean update(T bean, Connection connection) throws Exception;
	
	public T get(int id, Connection connection) throws Exception;
	
	public List<T> get(List<Integer> id, Connection connection) 
			throws Exception;
	
}
