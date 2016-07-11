package com.beans;

import java.sql.Date;

public class Bean {

	private int id;
	private boolean isDeleted;
	private Date creationTime;
	
	public Bean(int id, boolean isDeleted, Date dateCreated){
		setId(id);
		setDeleted(isDeleted);
		this.creationTime = dateCreated;
	}
	
	public Bean(){
		this(0, false, new Date(System.currentTimeMillis()));
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	
}
