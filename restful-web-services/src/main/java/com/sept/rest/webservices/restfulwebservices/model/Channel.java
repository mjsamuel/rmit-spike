package com.sept.rest.webservices.restfulwebservices.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "channels")
public class Channel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "datetime")
	private Date datetime;
	@Column(name = "archived")
	private Boolean archived;
	@Column(name = "visibility")
	private Visibility visibility;

	public enum Visibility {
		PUBLIC, PRIVATE
	};

	@ElementCollection
	@JsonIgnore
	@CollectionTable(name = "channelThreads")
	private List<Thread> threads;

	// Default constructor
	public Channel() {
	}

	// Constructor for creation of a new comment
	public Channel(String name, Visibility visibility, Date datetime) {
		super();
		this.name = name;
		this.visibility = visibility;
		this.datetime = datetime;
		archived = false;
		threads = new ArrayList<Thread>();
	}
	
	// Constructor for instantiating existing thread from serialization
	public Channel(Long id, String name, Date datetime, boolean archived, Visibility visibility, ArrayList<Thread> threads) {
		super();
		this.id = id;
		this.name = name;
		this.datetime = datetime;
		this.archived = archived;
		this.visibility = visibility;
		this.threads = threads;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public Boolean getArchived() {
		return archived;
	}

	public void setArchived(Boolean archived) {
		this.archived = archived;
	}

	public Visibility getVisibility() {
		return visibility;
	}

	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}

	public List<Thread> getThreads() {
		if(threads.isEmpty())
			return null;
		return threads;
	}

	public void setThreads(List<Thread> threads) {
		this.threads = threads;
	}

	public boolean addThread(Thread thread) {
		if (!threads.contains(thread)) {
			threads.add(thread);
			return true;
		}
		return false;
	}

	public boolean removeThread(Thread thread) {
		if (threads.contains(thread)) {
			threads.remove(thread);
			return true;
		}
		return false;
	}
}
