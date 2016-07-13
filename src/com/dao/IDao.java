package com.dao;

import java.sql.Connection;

public interface IDao<T> {

	public void create(T bean, Connection connection) throws Exception;
	
	public void delete(int id, Connection connection) throws Exception;
	
	public boolean update(T bean, Connection connection) throws Exception;
	
	public T get(int id, Connection connection) throws Exception;
		
}
