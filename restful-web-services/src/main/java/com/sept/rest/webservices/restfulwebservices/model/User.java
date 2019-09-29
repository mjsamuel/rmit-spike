package com.sept.rest.webservices.restfulwebservices.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.management.InvalidAttributeValueException;
import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
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
	public User(String email, String username, String password, String firstname, String lastname)
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

	public String getEmail() {
		return email;
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
	
	public List<Long> getSubscribedTo() {
		return subscribedTo;
	}
	
	public boolean isSubscribedTo(Long channelId) {
		boolean retVal = false;
		
		for (Long subscribedId : subscribedTo) {
			if (subscribedId == channelId) retVal = true;
		}
		
		return retVal;
	}

	// Setters
	public void setId(long id) {
		this.id = id;
	}
	
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
