package com.sept.rest.webservices.restfulwebservices.comment;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.management.InvalidAttributeValueException;

@Entity
public class Comment {
	@Id
	@GeneratedValue
	private long id;
	private Date datetime;
	private int upspikes;
	private int downspikes;
	private String content;
	private long replyId;
	private long threadId;
	private boolean archived;
	
	public Comment() {

	}

	public Comment(long id, Date datetime, int upspikes, int downspikes, String content, long replyId, long threadId, boolean archived) throws InvalidAttributeValueException {
		super();
		this.id = id;
		this.datetime = datetime;
		this.setUpspikes(upspikes);
		this.setDownspikes(downspikes);
		this.content = content;
		this.replyId = replyId;
		this.threadId = threadId;
		this.archived = archived;
	}

	// Getters
	public long getId() { return this.id; }
	public Date getDatetime() { return this.datetime; }
	public int getUpspikes() { return this.upspikes; }
	public int getDownspikes() { return this.downspikes; }
	public int getSpikes() { return this.upspikes - this.downspikes; }
	public float getSpikeRatio() { return (float)this.upspikes/((float)(this.upspikes + this.downspikes)); }
	public String getContent() { return this.content; }
	public long getReplyId() { return this.replyId; }
	public long getThreadId() { return this.threadId; }
	public boolean isArchived() { return this.archived; }

	// Setters
	public void setDatetime(Date datetime) { this.datetime = datetime; }
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

	public void setContent(String content) { this.content = content; }
	public void isArchived(boolean archived) { this.archived = archived; }	


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

	
}