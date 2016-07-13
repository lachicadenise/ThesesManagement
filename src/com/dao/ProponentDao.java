package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import com.beans.Proponent;
import com.utils.MySQLConnectionFactory;

public class ProponentDao implements IDao<Proponent>{

	@Override
	public void create(Proponent bean, Connection connection) throws Exception {
		// TODO Auto-generated method stub
		String query = "insert into proponents(thesisId, title, lastname, firstname, middlename) values(?, ?, ?, ?, ?)";
		PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
		statement.setInt(1, bean.getThesisId());
		statement.setString(2, bean.getTitle());
		statement.setString(3, bean.getLastname());
		statement.setString(4, bean.getFirstname());
		statement.setString(5, bean.getMiddlename());
		int rowsCreated = statement.executeUpdate();
		if(rowsCreated == 1){
			ResultSet resultSet = statement.getGeneratedKeys();
			if(resultSet.next()){
				bean.setId(resultSet.getInt(1));
			}
			resultSet.close();
			if(bean.getId() != 0){
				query = "select creationTime from proponents where id = ?";
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
		String query = "delete from proponents where id = ? limit 1";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, id);
		statement.executeUpdate();
		statement.close();
	}

	@Override
	public boolean update(Proponent bean, Connection connection) throws Exception {
		// TODO Auto-generated method stub
		String query = "update proponents set title = ?, lastname = ?, firstname = ?, middlename = ?, isDeleted = ? where id = ? limit 1";
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
	public Proponent get(int id, Connection connection) throws Exception {
		// TODO Auto-generated method stub
		Proponent proponent = null;
		String query = "select * from proponents where id = ? limit 1";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, id);
		ResultSet resultSet = statement.executeQuery();
		if(resultSet.next()){
			int thesisId = resultSet.getInt("thesisId");
			String title = resultSet.getString("title");
			String lastname = resultSet.getString("lastname");
			String firstname = resultSet.getString("firstname");
			String middlename = resultSet.getString("middlename");
			boolean isDeleted = resultSet.getBoolean("isDeleted");
			Timestamp creationTime = resultSet.getTimestamp("creationTime");
			proponent = new Proponent(id, thesisId, title, lastname, firstname, middlename, isDeleted, creationTime);
		}
		resultSet.close();
		statement.close();
		return proponent;
	}
	
	public static void main(String[] args) throws Exception{
		Connection connection = MySQLConnectionFactory.createConnection();
		ProponentDao dao = new ProponentDao();
		Proponent bean = dao.get(1, connection);
		dao.delete(bean.getId(), connection);
		connection.close();
	}
	
}
