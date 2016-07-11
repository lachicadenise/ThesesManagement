package com.beans;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Thesis extends Bean {

	private String title;
	private String summary;
	private List<Proponent> proponents;
	private List<Adviser> advisers;
	private List<Tag> tags;

	public Thesis(int id, String title, String summary, List<Proponent> proponents, List<Adviser> advisers, List<Tag> tags, boolean isDeleted, Date creationTime) {
		super(id, isDeleted, creationTime);
		setTitle(title);
		setSummary(summary);
		setProponents(proponents);
		setAdvisers(advisers);
		setTags(tags);
	}
	
	public Thesis(String title, String summary){
		this(0, title, summary, new ArrayList<Proponent>(), new ArrayList<Adviser>(), new ArrayList<Tag>(), false, new Date(System.currentTimeMillis()));
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getSummary() {
		return summary;
	}
	
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	public List<Proponent> getProponents() {
		return proponents;
	}
	
	public void setProponents(List<Proponent> proponents) {
		this.proponents = proponents;
	}
	
	public List<Adviser> getAdvisers() {
		return advisers;
	}
	
	public void setAdvisers(List<Adviser> advisers) {
		this.advisers = advisers;
	}
	
	public List<Tag> getTags() {
		return tags;
	}
	
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	
}
