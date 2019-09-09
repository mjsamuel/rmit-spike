package com.sept.rest.webservices.restfulwebservices.channel;

//Test User Class
public class User {

	private int sNo;
	private String password;
	private String fName;
	private String lName;
	private String email;
	private boolean isAdmin;
	private boolean archived;
	private int pixels;
	
	public User(int sNo, String fName)
	{
		this.sNo = sNo;
		this.fName = fName;
	}
	
	
	public String toString()
	{
		return String.format("SNo: %d | Name: %s\n", sNo, fName);
		
	}
}
