package com.sept.rest.webservices.restfulwebservices.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sept.rest.webservices.restfulwebservices.model.User;

@Service
public class UserService {

	// private EntityManagerFactory emf = Persistence.createEntityManagerFactory();
	// private EntityManager em = emf.createEntityManager();

	public List<User> getAllUsers() {
		return null;
	}

	public User getUserById(long id) {
		return null;
	}

	public boolean save(User user) {
		return false;
	}
}
