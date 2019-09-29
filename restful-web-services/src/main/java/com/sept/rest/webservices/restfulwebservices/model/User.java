

package com.sept.rest.webservices.restfulwebservices.model;

import javax.management.InvalidAttributeValueException;

public class User {

	private String email;
	private String password;
	private String studentNumber;
	private String firstName;

	@Column(name = "last_name")
	private String lastName;
	private boolean archived;
	private boolean admin;
	private int upspikes;
	private int downspikes;


	public User(String email, String password, String studentNumber, String firstName, String lastName, boolean archived, boolean admin, int upspikes, int downspikes) {
		this.email = email;
		this.password = password;
		this.studentNumber = studentNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.admin = false;
		this.archived = false;
		this.upspikes = 0;
		this.downspikes = 0;


	}

	public String getEmail() { return this.email;}

	public void setEmail(String email) {this.email = email;}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {this.password = password;}

	public String getStudentNumber() {
		return this.studentNumber;
	}

	public void setStudentNumber(String studentNumber) {this.studentNumber = studentNumber;}

	public String getFirstName() { return this.firstName; }

	public void setFirstName() {this.firstName = firstName;}

	public String getLastName() { return this.lastName; }

	public void setLastNameName() {this.lastName = lastName;}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
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
