package com.sept.rest.webservices.restfulwebservices.model;

import java.util.Date;
import org.ocpsoft.prettytime.PrettyTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.management.InvalidAttributeValueException;

@Entity
@Table(name = "comments")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	@Column(name = "datetime")
	private Date datetime;
	@Column(name = "user_id")
	private long userId;

	@Column(name = "upspikes")
	private int upspikes;

	@Column(name = "downspikes")
	private int downspikes;

	@Column(name = "content")
	private String content;

	@Column(name = "reply_id")
	private long replyId;

	@Column(name = "thread_id")
	private long threadId;

	@Column(name = "archived")
	private Boolean archived;
	
	public Comment() {

	}

	public Comment(long id, long userId, Date datetime, int upspikes, int downspikes, String content, long replyId, long threadId, boolean archived) throws InvalidAttributeValueException {
		super();
		this.id = id;
		this.userId = userId;
		this.datetime = datetime;
		this.setUpspikes(upspikes);
		this.setDownspikes(downspikes);
		this.content = content;
		this.replyId = replyId;
		this.threadId = threadId;
		this.archived = archived;
	}

	// Getters
	public long getId() { 
		return this.id;
	}

	public Date getDatetime() {
		return this.datetime;
	}

	public long getUserId() {
		return this.userId;
	}

	public int getUpspikes() {
		return this.upspikes;
	}

	public int getDownspikes() {
		return this.downspikes;
	}

	public int getSpikes() {
		return this.upspikes - this.downspikes;
	}

	public float getSpikeRatio() {
		return (float)this.upspikes/((float)(this.upspikes + this.downspikes));
	}

	public String getContent() {
		return this.content;
	}

	public long getReplyId() {
		return this.replyId;
	}

	public long getThreadId() {
		return this.threadId;
	}

	public Boolean isArchived() {
		return this.archived;
	}

	public String getTimeDelta() {
		PrettyTime p = new PrettyTime();
		return p.formatDuration(this.datetime);
	}

	// Setters
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public void setUpspikes(int upspikes) throws InvalidAttributeValueException { 
		if (upspikes >= 0) {
			this.upspikes = upspikes;		
		}
		else {
			throw new InvalidAttributeValueException("Number of upspikes must be positive");
		}
 	}

	public void setDownspikes(int downspikes) throws InvalidAttributeValueException { 
		if (downspikes >= 0) {
			this.downspikes = downspikes;		
		}
		else {
			throw new InvalidAttributeValueException("Number of downspikes must be positive");
		}
 	}

	public void setContent(String content) {
		this.content = content;
	}

	public void isArchived(boolean archived) {
		this.archived = archived;
	}	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("{ \"id\":%d, \"userId\":%d, \"datetime\":%tN, \"upspikes\":%d, \"downspikes\":%d, \"content\":%s, \"replyId\":%d, \"threadId\":%d, \"archive\":%b }",
			id, userId, datetime, upspikes, downspikes, content, replyId, threadId, archived);
	}

	
}