package com.sept.rest.webservices.restfulwebservices.model;

import java.util.ArrayList;
import java.util.List;

import javax.management.InvalidAttributeValueException;
import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "admin")
	private boolean admin;

	@Column(name = "email")
	private String email;

	@Column(name = "username")
	private String username;

	// Password that is encrypted using BCrypt
	@Column(name = "password")
	private String password;

	@Column(name = "firstName")
	private String firstName;

	@Column(name = "lastName")
	private String lastName;

	@Column(name = "upspikes")
	private long upspikes;

	@Column(name = "archived")
	private boolean archived;
	
	// Stores the channel IDs of channels the user is subscribed to
	@ElementCollection
	@CollectionTable(name = "subscribed_to")
	private List<Long> subscribedTo;

	// Default constructor
	public User() {

	}

	// Constructor for creation of a new user
	public User(long id, String email, String username, String password, String firstname, String lastname)
			throws InvalidAttributeValueException {
		super();
		this.id = id;
		admin = false;
		this.email = email;
		this.username = username;
		this.password = password;
		this.firstName = firstname;
		this.lastName = lastname;
		upspikes = 0;
		subscribedTo = new ArrayList<Long>();
		archived = false;
	}

	// Constructor for instantiating existing user from serialization
	public User(long id, boolean admin, String email, String username, String password, String firstname,
			String lastname, long upspikes, List<Long> subscribedTo, boolean archived)
			throws InvalidAttributeValueException {
		super();
		this.id = id;
		this.admin = admin;
		this.email = email;
		this.username = username;
		this.password = password;
		this.firstName = firstname;
		this.lastName = lastname;
		this.upspikes = upspikes;
		this.subscribedTo = subscribedTo;
		this.archived = archived;
	}

	// Getters
	public long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public long getUpspikes() {
		return upspikes;
	}

	// Setters
	public void setPassword(String password) {
		this.password = password;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}

//package com.sept.rest.webservices.restfulwebservices.model;
//
//public class User {
//
//	private String username;
//	private String password;
//	private String firstName;
//	private String lastName;
//		
//	public User(String username, String password, String firstname, String lastname) {
//		this.username = username;
//		this.password = password;
//		this.firstName = firstname;
//		this.lastName = lastname;
//		
//	}
//
//	public String getUsername() {
//		return this.username;
//	}
//
//	public String getPassword() {
//		return this.password;
//	}
//
//	public String getFristname() {
//		return this.firstName;
//	}
//
//	public String getLastName() {
//		return this.lastName;
//	}
//}