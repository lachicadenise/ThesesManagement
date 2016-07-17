package com.beans;

import java.sql.Timestamp;

public class Proponent extends Bean {

	private int thesisId;
	private String title;
	private String lastname;
	private String firstname;
	private String middlename;
	
	public Proponent(int id, int thesisId, String title, String lastname, String firstname, String middlename, boolean isDeleted, Timestamp creationTime) {
		super(id, isDeleted, creationTime);
		setThesisId(thesisId);
		setTitle(title);
		setLastname(lastname);
		setFirstname(firstname);
		setMiddlename(middlename);
	}

	public Proponent(int thesisId, String title, String lastname, String firstname, String middlename){
		this(0, thesisId, title, lastname, firstname, middlename, false, null);
	}

	public Proponent(){
		//used by jackson parser
	}
	
	public int getThesisId(){
		return thesisId;
	}
	
	public void setThesisId(int thesisId){
		this.thesisId = thesisId;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setTitle(String title){
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
