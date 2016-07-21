package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.beans.UserAccount;

public class UserAccountDao implements IDao<UserAccount> {

	public List<UserAccount> search(String value, int startFrom, int take, Connection connection) throws SQLException{
		List<UserAccount> userAccounts = new ArrayList<UserAccount>();
		String query = 
				"select "
					+ "id, "
					+ "username, "
					+ "lastname, "
					+ "firstname, "
					+ "middlename "
				+ "from userAccounts where "
				+ "("
					+ "lower(username) like ? or "
					+ "lower(lastname) like ? or "
					+ "lower(firstname) like ? or "
					+ "lower(middlename) like ?"
				+ ") "
				+ "and isDeleted = ? "
				+ "and username != ? "
				+ "order by lastname asc "
				+ "limit " + startFrom + ", " + take;
		value = "%" + value.toLowerCase() + "%";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, value);
		statement.setString(2, value);
		statement.setString(3, value);
		statement.setString(4, value);
		statement.setBoolean(5, false);
		statement.setString(6, "admin");
		ResultSet resultSet = statement.executeQuery();
		while(resultSet.next()){
			UserAccount userAccount = new UserAccount();
			userAccount.setId(resultSet.getInt("id"));
			userAccount.setUsername(resultSet.getString("username"));
			userAccount.setLastname(resultSet.getString("lastname"));
			userAccount.setFirstname(resultSet.getString("firstname"));
			userAccount.setMiddlename(resultSet.getString("middlename"));
			userAccounts.add(userAccount);
		}
		resultSet.close();
		statement.close();
		return userAccounts;
	}
	
	public int count(String searchValue, Connection connection) throws SQLException{
		int count = 0;
		searchValue = searchValue
			    .replace("!", "!!")
			    .replace("%", "!%")
			    .replace("_", "!_")
			    .replace("[", "![");
		String query = 
				"select count(*) "
				+ "from userAccounts where "
				+ "username like ? or "
				+ "lastname like ? or "
				+ "firstname like ? or "
				+ "middlename like ?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, "%" + searchValue + "%");
		statement.setString(2, "%" + searchValue + "%");
		statement.setString(3, "%" + searchValue + "%");
		statement.setString(4, "%" + searchValue + "%");
		ResultSet resultSet = statement.executeQuery();
		if(resultSet.next()){
			count = resultSet.getInt(1);
		}
		resultSet.close();
		statement.close();
		return count;
	}
	
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
