package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import com.beans.Adviser;

public class AdviserDao implements IDao<Adviser> {

	@Override
	public void create(Adviser bean, Connection connection) 
			throws Exception {
		// TODO Auto-generated method stub
		String query = "insert into advisers(title, lastname, firstname, middlename) values(?, ?, ?, ?)";
		PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
		statement.setString(1, bean.getTitle());
		statement.setString(2, bean.getLastname());
		statement.setString(3, bean.getFirstname());
		statement.setString(4, bean.getMiddlename());
		int rowsCreated = statement.executeUpdate();
		if(rowsCreated == 1){
			ResultSet resultSet = statement.getGeneratedKeys();
			if(resultSet.next()){
				bean.setId(resultSet.getInt(1));
			}
			resultSet.close();
			if(bean.getId() != 0){
				query = "select creationTime from advisers where id = ?";
				statement = connection.prepareStatement(query);
				statement.setInt(1, bean.getId());
				resultSet = statement.executeQuery();
				if(resultSet.next()){
					Timestamp creationTime = resultSet.getTimestamp("creationTime");
					bean.setCreationTime(creationTime);
				}
			}
		}
		statement.close();
	}
	
	@Override
	public void delete(int id, Connection connection) 
			throws Exception {
		// TODO Auto-generated method stub
		String query = "delete from advisers where id = ? limit 1";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, id);
		statement.executeUpdate();
		statement.close();
	}

	@Override
	public boolean update(Adviser bean, Connection connection) 
			throws Exception {
		// TODO Auto-generated method stub
		String query = "update advisers set title = ?, lastname = ?, firstname = ?, middlename = ?, isDeleted = ? where id = ? limit 1";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, bean.getTitle());
		statement.setString(2, bean.getLastname());
		statement.setString(3, bean.getFirstname());
		statement.setString(4, bean.getMiddlename());
		statement.setBoolean(5, bean.isDeleted());
		statement.setInt(6, bean.getId());
		int affectedRows = statement.executeUpdate();
		statement.close();
		return affectedRows == 1;
	}

	@Override
	public Adviser get(int id, Connection connection) 
			throws Exception {
		// TODO Auto-generated method stub
		Adviser adviser = null;
		String query = "select * from advisers where id = ? limit 1";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, id);
		ResultSet resultSet = statement.executeQuery();
		if(resultSet.next()){
			String title = resultSet.getString("title");
			String lastname = resultSet.getString("lastname");
			String firstname = resultSet.getString("firstname");
			String middlename = resultSet.getString("middlename");
			boolean isDeleted = resultSet.getBoolean("isDeleted");
			Timestamp creationTime = resultSet.getTimestamp("creationTime");
			adviser = new Adviser(id, title, lastname, firstname, middlename, isDeleted, creationTime);
		}
		resultSet.close();
		statement.close();
		return adviser;
	}
	
}
