package com.sept.rest.webservices.restfulwebservices.model;

import org.ocpsoft.prettytime.PrettyTime;

import java.util.Date;

import javax.management.InvalidAttributeValueException;
import javax.persistence.*;

@Entity
@Table(name = "threads")
public class Thread {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "datetime")
	private Date datetime;

	@Column(name = "content", nullable = false)
	private String content;

	@Column(name = "archived", columnDefinition="boolean default false")
	private Boolean archived = false;

	@Column(name = "upspikes", columnDefinition="integer default 0")
	private Integer upspikes = 0;

	@Column(name = "downspikes", columnDefinition="integer default 0")
	private Integer downspikes = 0;

	@Column(name = "author_id", nullable = false)
	private Long authorId;

	@Column(name = "channel_id", nullable = false)
	private Long channelId;

	@Column(name = "taggedChannels")
	private String taggedChannels;
	
	public Thread() {
		super();
	}
	
	public Thread(Long id, String title, Date datetime, String content, Boolean archived, Integer upspikes, Integer downspikes, Long authorId, Long channelId, String taggedChannels) {
		super();
		this.title = title;
		this.datetime = datetime;
		this.content = content;
		this.archived = false;
		this.upspikes = 0;	
		this.downspikes = 0;
		this.authorId = authorId;
		this.channelId = channelId;
		this.setTaggedChannels(taggedChannels);
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

	public String getTimeDelta() {
		PrettyTime p = new PrettyTime(new Date());
		return p.format(this.datetime);
	}

	public Long getAuthorId() {
		return this.authorId;
	}

	public void setOp(Long authorId) {
		this.authorId = authorId;
	}

	public Long getChannelId() {
		return this.channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public String getTaggedChannels() {
		return taggedChannels;
	}

	public void setTaggedChannels(String taggedChannels) {
		this.taggedChannels = taggedChannels;
	}
}
