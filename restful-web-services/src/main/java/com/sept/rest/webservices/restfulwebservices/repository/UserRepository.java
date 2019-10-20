package com.sept.rest.webservices.restfulwebservices.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sept.rest.webservices.restfulwebservices.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
	
	ArrayList<User> findByUsernameContainingIgnoreCase(String username);

	User findByEmail(String email);

}
