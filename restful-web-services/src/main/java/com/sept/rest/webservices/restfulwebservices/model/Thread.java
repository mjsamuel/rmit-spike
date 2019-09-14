package com.sept.rest.webservices.restfulwebservices.model;

import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "threads")
public class Thread {
	
	@Id
	@GeneratedValue
	private Long id;
	@NotBlank
	private String title;
	@NotBlank 
	private Date datetime;
	@NotBlank
	private String content;
	@NotBlank
	private boolean archived;
	@NotBlank
	private int upspikes, downspikes;
	@NotBlank
	private String op;
	
	public Thread() {
		super();
	}
	
	public Thread(String title, String content) {
		super();
		this.content = content;
		this.title = title;
		this.datetime = new Date();
		this.archived = false;
		this.upspikes = 0;
		this.downspikes = 0;
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

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
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

	public void setUpspikes(int upspikes) {
		this.upspikes = upspikes;
	}

	public int getDownspikes() {
		return downspikes;
	}

	public void setDownspikes(int downspikes) {
		this.downspikes = downspikes;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}
}
