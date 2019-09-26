package com.sept.rest.webservices.restfulwebservices.model;

public class User {

	private String username;
	private String password;
	private String firstName;
	private String lastName;
		
	public User(String username, String password, String firstname, String lastname) {
		this.username = username;
		this.password = password;
		this.firstName = firstname;
		this.lastName = lastname;
		
	}

	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

	public String getFristname() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}
}