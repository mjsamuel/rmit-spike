package com.sept.rest.webservices.restfulwebservices.thread;

import java.util.Date;

import javax.management.InvalidAttributeValueException;
import javax.persistence.*;

@Entity
@Table(name = "threads")
public class Thread {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String datetime;
	private String content;
	private boolean archived;
	private int upspikes, downspikes;
	private String op;
	
	public Thread() {
		super();
	}
	
	public Thread(Long id, String title, String content, boolean archived, int upspikes, int downspikes, String op) {
		super();
		this.title = title;
		this.datetime =  new Date().toString();
		this.content = content;
		this.archived = false;
		this.upspikes = 0;
		this.downspikes = 0;
		this.op = op;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isArchived() {
		return archived;
	}

	public void setArchived(boolean archived) {
		this.archived = archived;
	}

	public int getUpspikes() {
		return upspikes;
	}

	public void setUpspikes(int upspikes) throws InvalidAttributeValueException {
		if(upspikes >= 0)
			this.upspikes=upspikes;
		else
			throw new InvalidAttributeValueException("Number of upspikes must be positive.");
	}

	public int getDownspikes() {
		return downspikes;
	}

	public void setDownspikes(int downspikes) throws InvalidAttributeValueException {
		if(downspikes >= 0)
			this.downspikes = downspikes;
		else
			throw new InvalidAttributeValueException("Number of downspikes must be positive.");
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}
}
