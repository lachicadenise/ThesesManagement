package com.beans;

import java.sql.Timestamp;

public class Bean {

	private int id;
	private boolean isDeleted;
	private Timestamp creationTime;
	
	public Bean(int id, boolean isDeleted, Timestamp dateCreated){
		setId(id);
		setDeleted(isDeleted);
		this.creationTime = dateCreated;
	}
	
	public Bean(){
		this(0, false, null);
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

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}
	
}
