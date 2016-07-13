package com.beans;

import java.sql.Timestamp;

public class UserAccount extends Bean {

	private String username;
	private String password;
	private String lastname;
	private String firstname;
	private String middlename;
	
	public UserAccount(int id, String username, String password, String lastname, String firstname, String middlename, boolean isDeleted, Timestamp creationTime) {
		super(id, isDeleted, creationTime);
		setUsername(username);
		setPassword(password);
		setLastname(lastname);
		setFirstname(firstname);
		setMiddlename(middlename);
	}	
	
	public UserAccount(String username, String password, String lastname, String firstname, String middlename){
		this(0, username, password, lastname, firstname, middlename, false, null);
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getMiddlename() {
		return middlename;
	}
	
	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}
	
}
