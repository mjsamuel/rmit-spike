package com.sept.rest.webservices.restfulwebservices.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

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
	private String datetime;
	@Column(name = "archived")
	private Boolean archived;
	@Column(name = "visibility")
	private Visibility visibility;

	public enum Visibility {
		SHARED, EXCLUSIVE
	};

	// @Julian: The Lists need to have appropriate annotations to make them work
	// with database so
	// commented out for now. Perhaps look at @ManyToOne

	// Stores a list of all Users subscribed to the channel.
	// private List<User> subscribers = new ArrayList<User>();

	// Stores a list of all threads in the channel
	// private List<Thread> threads = new ArrayList<Thread>();

	public Channel() {
		super();
	}

	public Channel(Long id, String name, Visibility visibility, Boolean archived) {
		super();
		this.id = id;
		this.name = name;
		this.datetime = new Date().toString();
		this.visibility = visibility;
		this.archived = archived;
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

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
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

	// Add thread to Channel
	// public boolean addThread(Thread thread)
	// {
	// if(!threads.contains(thread))
	// {
	// threads.add(thread);
	// return true;
	// }
	// return false;
	// }

	// Remove Thread from Channel
	// public boolean removeThread(Thread thread)
	// {
	// if(threads.contains(thread))
	// {
	// threads.remove(thread);
	// return true;
	// }
	// return false;
	// }

	// Subscribe User to Channel
	// public boolean subscribe(User user)
	// {
	// if(!subscribers.contains(user))
	// {
	// subscribers.add(user);
	// return true;
	// }
	// return false;
	// }

	// Unsubscribe User from Channel
	// public boolean unSubscribe(User user)
	// {
	// // If user is subscribed
	// if(subscribers.contains(user))
	// {
	// subscribers.remove(user);
	// return true;
	// }
	// return false;
	// }

	// Delete Channel

	// Channel Chat

	// Send Notification to all users that are subscribed.

	// Return all subscribers to channel
	// public List<User> displaySubscribers()
	// {
	// if(subscribers.isEmpty())
	// return null;

	// return subscribers;
	// }

	// Return all threads in Channel
	// public List<Thread> displayThreads()
	// {
	// if(threads.isEmpty())
	// return null;

	// return threads;
	// }

	// toString for console testing purposes without database
	// public String toString()
	// {
	// StringBuffer buffer = new StringBuffer();
	// buffer.append(String.format("Channel: %s - %s\nCreated: %s\nVisibility: %s |
	// Archived: %b\n\nSubscribers:\n", id, name, datetime, visibility,
	// archived));

	// if(subscribers.isEmpty())
	// buffer.append(String.format("NULL\n"));

	// for(User user : subscribers)
	// {
	// buffer.append(String.format("%s", user));
	// }

	// buffer.append(String.format("\nThreads:\n"));

	// if(threads.isEmpty())
	// buffer.append(String.format("NULL\n"));

	// for(Thread thread : threads)
	// {
	// buffer.append(String.format("%s", thread));
	// }

	// return buffer.toString();
	// }
}
