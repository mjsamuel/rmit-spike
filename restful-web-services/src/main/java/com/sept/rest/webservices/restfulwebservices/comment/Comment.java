package com.sept.rest.webservices.restfulwebservices.comment;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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

	public Comment(long id, Date datetime, int upspikes, int downspikes, String content, long replyId, long threadId, boolean archived) {
		super();
		this.id = id;
		this.datetime = datetime;
		this.upspikes = upspikes;
		this.downspikes = downspikes;
		this.content = content;
		this.replyId = replyId;
		this.threadId = threadId;
		this.archived = archived;
	}

	public long getId() {
		return this.id;
	}

	public Date getDatetime() {
		return this.datetime;
	}

	public int getSpikes() {
		return this.upspikes - this.downspikes;
	}

	public float getSpikeRatio() {
		return this.upspikes/(this.upspikes + this.downspikes);
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

	public boolean isArchived() {
		return this.archived;
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

	
}