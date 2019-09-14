package com.sept.rest.webservices.restfulwebservices.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "channels")
public class Channel
{
	// Instance Variables
	@Id
	@GeneratedValue
	private long id;
	@NotBlank
	private String name;
	@NotBlank
	private Date dateCreated;
	@NotBlank
	private Boolean archived;
	@NotBlank
	private Visibility visibility;
	
	public enum Visibility{SHARED,	EXCLUSIVE};
	
	// Stores a list of all Users subscribed to the channel.
	private List<User> subscribers = new ArrayList<User>();
	
	// Stores a list of all threads in the channel
	private List<Thread> threads = new ArrayList<Thread>();
	
	public Channel()
	{
		super();
	}
	
	// Constructor
	public Channel(long id, String name, Date dateCreated, Visibility visibility, Boolean archived)
	{
		super();
		this.id = id;
		this.name = name;
		this.dateCreated = dateCreated;
		this.visibility = visibility;		
		this.archived = archived;
	}
	
	// Add thread to Channel
	public boolean addThread(Thread thread)
	{
		if(!threads.contains(thread))
		{
			threads.add(thread);
			return true;
		}
		return false;
	}
	
	// Remove Thread from Channel
	public boolean removeThread(Thread thread)
	{
		if(threads.contains(thread))
		{
			threads.remove(thread);
			return true;
		}
		return false;
	}
	
	// Subscribe User to Channel
	public boolean subscribe(User user)
	{
		if(!subscribers.contains(user))
		{
			subscribers.add(user);
			return true;
		}
		return false;
	}

	// Unsubscribe User from Channel
	public boolean unSubscribe(User user)
	{
		// If user is subscribed
		if(subscribers.contains(user))
		{
			subscribers.remove(user);
			return true;
		}
		return false;
	}
	
	// Delete Channel
	
	// Channel Chat
		
	// Send Notification to all users that are subscribed.
	
	// Get Channel ID
	public long getChannelId()
	{
		return id;
	}
	
	// Set Channel Name
	public void setChannelId(long id)
	{
		this.id = id;
	}
	
	// Get Channel Name
	public String getChannelName()
	{
		return name;
	}
	
	// Set Channel Name
	public void setChannelName(String name)
	{
		this.name = name;
	}
	
	// Set Date of Channel
	public Date getDate()
	{
		return dateCreated;
	}
	
	// Set Date of Channel
	public void setDate(Date date)
	{
		this.dateCreated = date;
	}
	
	// Check visibility of Channel
	public Visibility getVisibility()
	{
		return visibility;
	}
	
	// Change visibility of Channel
	public void setVisibility(Visibility visibility)
	{
		this.visibility = visibility;
	}
	
	// Check if status is archived
	public Boolean isArchived()
	{
		return archived;
	}
	
	// Set channel to archived
	public void setArchived(Boolean archived)
	{
		this.archived = archived;
	}
	
	// Return all subscribers to channel
	public List<User> displaySubscribers()
	{
		if(subscribers.isEmpty())
			return null;
		
		return subscribers;
	}
	
	// Return all threads in Channel
	public List<Thread> displayThreads()
	{
		if(threads.isEmpty())
			return null;
					
		return threads;
	}
	
	// toString for console testing purposes without database
	public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append(String.format("Channel: %s - %s\nCreated: %s\nVisibility: %s | Archived: %b\n\nSubscribers:\n", id, name, dateCreated, visibility, archived));
		
		if(subscribers.isEmpty())
			buffer.append(String.format("NULL\n"));
		
		for(User user : subscribers)
		{
			buffer.append(String.format("%s", user));
		}
		
		buffer.append(String.format("\nThreads:\n"));
		
		if(threads.isEmpty())
			buffer.append(String.format("NULL\n"));

		for(Thread thread : threads)
		{
			buffer.append(String.format("%s", thread));
		}
		
		return buffer.toString();
	}
}
