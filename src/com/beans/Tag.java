package com.beans;

import java.sql.Timestamp;

public class Tag extends Bean {
	
	private String name;
	
	public Tag(int id, String name, boolean isDeleted, Timestamp creationTime) {
		super(id, isDeleted, creationTime);
		setName(name);
	}
	
	public Tag(String name){
		this(0, name, false, null);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
