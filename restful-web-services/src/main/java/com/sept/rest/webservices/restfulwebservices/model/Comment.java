package com.sept.rest.webservices.restfulwebservices.model;

import java.util.Date;
import org.ocpsoft.prettytime.PrettyTime;

import javax.persistence.*;
import javax.management.InvalidAttributeValueException;

@Entity
@Table(name = "comments")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "datetime")
	private Date datetime;

	@Column(name = "author_id", nullable = false)
	private Long authorId;

	@Column(name = "upspikes", columnDefinition="integer default 0")
	private Integer upspikes = 0;

	@Column(name = "downspikes", columnDefinition="integer default 0")
	private Integer downspikes = 0;

	@Lob
	@Column(name = "content", nullable = false)
	private String content;

	@Column(name = "reply_id", columnDefinition="integer default 0")
	private Long replyId = new Long(0);

	@Column(name = "thread_id", nullable = false)
	private Long threadId;

	@Column(name = "archived", columnDefinition="boolean default false")
	private Boolean archived = false;

	// Default constructor
	public Comment() {
	}

	// Constructor for creation of a new thread
	public Comment(Long authorId, Date datetime, String content, Long replyId, Long threadId) throws InvalidAttributeValueException {
		super();
		this.id = id;
		this.authorId = authorId;
		this.datetime = datetime;
		this.setUpspikes(0);
		this.setDownspikes(0);
		this.content = content;
		this.replyId = replyId;
		this.threadId = threadId;
		this.archived = false;
	}

	// Constructor for instantiating existing thread from serialization
	public Comment(Long id, Long authorId, Date datetime, Integer upspikes, Integer downspikes, String content, Long replyId, Long threadId, Boolean archived) throws InvalidAttributeValueException {
		super();
		this.id = id;
		this.authorId = authorId;
		this.datetime = datetime;
		this.setUpspikes(upspikes);
		this.setDownspikes(downspikes);
		this.content = content;
		this.replyId = replyId;
		this.threadId = threadId;
		this.archived = archived;
	}

	// Getters
	public Long getId() {
		return this.id;
	}

	public Date getDatetime() {
		return this.datetime;
	}

	public Long getAuthorId() {
		return this.authorId;
	}

	public Integer getUpspikes() {
		return this.upspikes;
	}

	public Integer getDownspikes() {
		return this.downspikes;
	}

	public Integer getSpikes() {
		try {
			return this.upspikes - this.downspikes;
		}
		catch (NullPointerException ex) {
			return new Integer(0);
		}
	}

	public Float getSpikeRatio() {
		try {
			Float ratio = this.upspikes.floatValue()/((this.upspikes.floatValue() + this.downspikes.floatValue()));
			if (Float.isNaN(ratio)) {
				return new Float(1.00);
			}
			return ratio;
		}
		catch (NullPointerException ex) {
			return new Float(1.00);
		}

	}

	public String getContent() {
		return this.content;
	}

	public Long getReplyId() {
		return this.replyId;
	}

	public Long getThreadId() {
		return this.threadId;
	}

	public Boolean getArchived() {
		return this.archived;
	}

	public String getTimeDelta() {
		PrettyTime p = new PrettyTime(new Date());
		return p.format(this.datetime);
	}

	// Setters
	public void setId(Long id) { this.id = id;}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public void setUpspikes(Integer upspikes) throws InvalidAttributeValueException {
		if (upspikes >= 0) {
			this.upspikes = upspikes;
		}
		else {
			throw new InvalidAttributeValueException("Number of upspikes must be positive");
		}
 	}

	public void setDownspikes(Integer downspikes) throws InvalidAttributeValueException {
		if (downspikes >= 0) {
			this.downspikes = downspikes;
		}
		else {
			throw new InvalidAttributeValueException("Number of downspikes must be positive");
		}
 	}

 	public void setThreadId(Long threadId) {
 		this.threadId = threadId;
 	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setArchived(Boolean archived) {
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
		return String.format("{ \"id\":%d, \"authorId\":%d, \"datetime\":%tN, \"upspikes\":%d, \"downspikes\":%d, \"content\":%s, \"replyId\":%d, \"threadId\":%d, \"archive\":%b }",
			id, authorId, datetime, upspikes, downspikes, content, replyId, threadId, archived);
	}


}
