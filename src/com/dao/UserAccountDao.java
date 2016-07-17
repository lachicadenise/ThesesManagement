package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.beans.UserAccount;

public class UserAccountDao implements IDao<UserAccount> {

	public boolean usernameExists(String username, Connection connection) throws SQLException{
		boolean usernameExists = false;
		
		String query = "select exists(select * from userAccounts where username = ? limit 1)";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, username);
		ResultSet resultSet = statement.executeQuery();
		if(resultSet.next()){
			usernameExists = resultSet.getBoolean(1);
		}
		resultSet.close();
		statement.close();
		
		return usernameExists;
	}
	
	@Override
	public void create(UserAccount bean, Connection connection) 
			throws Exception {
		// TODO Auto-generated method stub
		String query = "insert into userAccounts(username, password, lastname, firstname, middlename) values(?, ?, ?, ?, ?)";
		PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
		statement.setString(1, bean.getUsername());
		statement.setString(2, bean.getPassword());
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
				query = "select creationTime from userAccounts where id = ? limit 1";
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
		String query = "delete from userAccounts where id = ? limit 1";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, id);
		statement.executeUpdate();
		statement.close();
	}

	@Override
	public boolean update(UserAccount bean, Connection connection) 
			throws Exception {
		// TODO Auto-generated method stub
		String query = 
				"update userAccounts set "
				+ "username = ?, "
				+ "password = ?, "
				+ "lastname = ?, "
				+ "firstname = ?, "
				+ "middlename = ?, "
				+ "isDeleted = ? "
				+ "where id = ? limit 1";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, bean.getUsername());
		statement.setString(2, bean.getPassword());
		statement.setString(3, bean.getLastname());
		statement.setString(4, bean.getFirstname());
		statement.setString(5, bean.getMiddlename());
		statement.setBoolean(6, bean.isDeleted());
		statement.setInt(7, bean.getId());
		int affectedRows = statement.executeUpdate();
		statement.close();
		return affectedRows == 1;
	}

	@Override
	public UserAccount get(int id, Connection connection) 
			throws Exception {
		// TODO Auto-generated method stub
		UserAccount userAccount = null;
		String query = "select * from userAccounts where id = ? limit 1";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, id);
		ResultSet resultSet = statement.executeQuery();
		if(resultSet.next()){
			String username = resultSet.getString("username");
			String password = resultSet.getString("password");
			String lastname = resultSet.getString("lastname");
			String firstname = resultSet.getString("firstname");
			String middlename = resultSet.getString("middlename");
			boolean isDeleted = resultSet.getBoolean("isDeleted");
			Timestamp creationTime = resultSet.getTimestamp("creationTime");
			userAccount = new UserAccount(id, username, password, lastname, firstname, middlename, isDeleted, creationTime);
		}
		resultSet.close();
		statement.close();
		return userAccount;
	}

}
