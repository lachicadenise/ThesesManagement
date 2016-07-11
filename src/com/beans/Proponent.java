package com.beans;

import java.sql.Date;

public class Proponent extends Bean {

	private String lastname;
	private String firstname;
	private String middlename;
	
	public Proponent(int id, String lastname, String firstname, String middlename, boolean isDeleted, Date creationTime) {
		super(id, isDeleted, creationTime);
		setLastname(lastname);
		setFirstname(firstname);
		setMiddlename(middlename);
	}

	public Proponent(String lastname, String firstname, String middlename){
		this(0, lastname, firstname, middlename, false, new Date(System.currentTimeMillis()));
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
