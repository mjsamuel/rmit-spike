package com.sept.rest.webservices.restfulwebservices.channel;

// Test Thread Class
public class Thread {

	private int id;
	private String title;
	
	public Thread(int id, String title)
	{
		this.id = id;
		this.title = title;
	}
	
	public String toString()
	{
		return String.format("Thread ID: %d | Title: %s\n", id, title);
	}
}
