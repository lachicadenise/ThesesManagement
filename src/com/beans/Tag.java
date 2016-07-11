package com.beans;

import java.sql.Date;

public class Tag extends Bean {
	
	private String name;
	
	public Tag(int id, String name, boolean isDeleted, Date creationTime) {
		super(id, isDeleted, creationTime);
		setName(name);
	}
	
	public Tag(String name){
		this(0, name, false, new Date(System.currentTimeMillis()));
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
