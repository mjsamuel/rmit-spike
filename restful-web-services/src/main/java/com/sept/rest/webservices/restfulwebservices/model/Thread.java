package com.sept.rest.webservices.restfulwebservices.model;

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
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "datetime")
	private String datetime;
	
	@Column(name = "content")
	private String content;
	
	@Column(name = "archived")
	private boolean archived;
	
	@Column(name = "upspikes")
	private int upspikes;
	
	@Column(name = "downspikes")
	private int downspikes;
	
	// User ID of original poster
	@Column(name = "user_id")
	private long userId;
	
	// Channel ID of the primary channel
	@Column(name = "primary_channel")
	private long primaryChannel;
	
	@Column(name = "tagged_channels")
	private String taggedChannels;
	
	public Thread() {
		super();
	}
	
	// Constructor for creation of a new thread 
	public Thread(String title, String content, long userId, long primaryChannel) {
		super();
		this.id = id;
		this.title = title;
		archived = false;
		upspikes = 0;
		downspikes = 0;
		this.userId = userId;
		this.primaryChannel = primaryChannel;
	}
	
	// Constructor for instantiating existing thread from serialization
	public Thread(Long id, String title, String content, boolean archived, int upspikes, int downspikes, long userId, long primaryChannel, String taggedChannels) {
		super();
		this.title = title;
		this.datetime =  new Date().toString();
		this.content = content;
		this.archived = false;
		this.upspikes = 0;	
		this.downspikes = 0;
		this.userId = userId;
		this.primaryChannel = primaryChannel;
		this.taggedChannels = taggedChannels;
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

	public long getUserId() {
		return userId;
	}

	public void setOp(long userId) {
		this.userId = userId;
	}

	public long getPrimaryChannel() {
		return primaryChannel;
	}

	public void setPrimaryChannel(long primaryChannel) {
		this.primaryChannel = primaryChannel;
	}

	public String getTaggedChannels() {
		return taggedChannels;
	}

	public void setTaggedChannels(String taggedChannels) {
		this.taggedChannels = taggedChannels;
	}
}
