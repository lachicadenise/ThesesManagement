package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import com.beans.Tag;
import com.utils.MySQLConnectionFactory;

public class TagDao implements IDao<Tag>{

	@Override
	public void create(Tag bean, Connection connection) throws Exception {
		// TODO Auto-generated method stub
		String query = "insert into tags(name) values(?)";
		PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
		statement.setString(1, bean.getName());
		int rowsCreated = statement.executeUpdate();
		if(rowsCreated == 1){
			ResultSet resultSet = statement.getGeneratedKeys();
			if(resultSet.next()){
				bean.setId(resultSet.getInt(1));
			}
			resultSet.close();
			if(bean.getId() != 0){
				query = "select creationTime from tags where id = ?";
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
	public void delete(int id, Connection connection) throws Exception {
		// TODO Auto-generated method stub
		String query = "delete from tags where id = ? limit 1";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, id);
		statement.executeUpdate();
		statement.close();
	}

	@Override
	public boolean update(Tag bean, Connection connection) throws Exception {
		// TODO Auto-generated method stub
		String query = "update tags set name = ?, isDeleted = ? where id = ? limit 1";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, bean.getName());
		statement.setBoolean(2, bean.isDeleted());
		statement.setInt(3, bean.getId());
		int affectedRows = statement.executeUpdate();
		statement.close();
		return affectedRows == 1;
	}

	@Override
	public Tag get(int id, Connection connection) throws Exception {
		// TODO Auto-generated method stub
		Tag Tag = null;
		String query = "select * from tags where id = ? limit 1";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, id);
		ResultSet resultSet = statement.executeQuery();
		if(resultSet.next()){
			String name = resultSet.getString("title");
			boolean isDeleted = resultSet.getBoolean("isDeleted");
			Timestamp creationTime = resultSet.getTimestamp("creationTime");
			Tag = new Tag(id, name, isDeleted, creationTime);
		}
		resultSet.close();
		statement.close();
		return Tag;
	}
	
	public static void main(String[] args) throws Exception{
		Connection connection = MySQLConnectionFactory.createConnection();
		TagDao dao = new TagDao();
		Tag bean = new Tag("Electronics");
		dao.create(bean, connection);
		connection.close();
	}

}
