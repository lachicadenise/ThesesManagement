package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import com.beans.Thesis;

public class ThesisDao implements IDao<Thesis> {

	@Override
	public void create(Thesis bean, Connection connection) 
			throws Exception {
		// TODO Auto-generated method stub
		String query = "insert into theses(title, summary) values(?, ?)";
		PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
		statement.setString(1, bean.getTitle());
		statement.setString(2, bean.getSummary());
		int rowsCreated = statement.executeUpdate();
		if(rowsCreated == 1){
			ResultSet resultSet = statement.getGeneratedKeys();
			if(resultSet.next()){
				bean.setId(resultSet.getInt(1));
			}
			resultSet.close();
			if(bean.getId() != 0){
				query = "select creationTime from theses where id = ? limit 1";
				statement = connection.prepareStatement(query);
				statement.setInt(1, bean.getId());
				resultSet = statement.executeQuery();
				if(resultSet.next()){
					Timestamp creationTime = resultSet.getTimestamp("creationTime");
					bean.setCreationTime(creationTime);
				}
				resultSet.close();
			}
		}
		statement.close();
	}

	@Override
	public void delete(int id, Connection connection) 
			throws Exception {
		// TODO Auto-generated method stub
		String query = "delete from theses where id = ? limit 1";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, id);
		statement.executeUpdate();
		statement.close();
	}

	@Override
	public boolean update(Thesis bean, Connection connection) 
			throws Exception {
		// TODO Auto-generated method stub
		String query = "update theses set title = ?, summary = ?, isDeleted = ? where id = ? limit 1";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, bean.getTitle());
		statement.setString(2, bean.getSummary());
		statement.setBoolean(3, bean.isDeleted());
		statement.setInt(4, bean.getId());
		int affectedRows = statement.executeUpdate();
		statement.close();
		return affectedRows == 1;
	}

	@Override
	public Thesis get(int id, Connection connection) 
			throws Exception {
		// TODO Auto-generated method stub
		Thesis thesis = null;
		String query = "select * from theses where id = ? limit 1";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, id);
		ResultSet resultSet = statement.executeQuery();
		if(resultSet.next()){
			String title = resultSet.getString("title");
			String summary = resultSet.getString("summary");
			boolean isDeleted = resultSet.getBoolean("isDeleted");
			Timestamp creationTime = resultSet.getTimestamp("creationTime");
			thesis = new Thesis(id, title, summary, isDeleted, creationTime);
		}
		resultSet.close();
		statement.close();
		return thesis;
	}

}
