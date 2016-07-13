package com.beans;

import java.sql.Timestamp;

public class Adviser extends Bean{

	private String title;
	private String lastname;
	private String firstname;
	private String middlename;	
	
	public Adviser(int id, String title, String lastname, String firstname, String middlename, boolean isDeleted, Timestamp creationTime) {
		super(id, isDeleted, creationTime);
		setTitle(title);
		setLastname(lastname);
		setFirstname(firstname);
		setMiddlename(middlename);
	}
	
	public Adviser(String title, String lastname, String firstname, String middlename){
		this(0, title, lastname, firstname, middlename, false, null);
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
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
