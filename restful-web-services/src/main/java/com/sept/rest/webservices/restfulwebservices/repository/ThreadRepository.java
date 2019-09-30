package com.sept.rest.webservices.restfulwebservices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sept.rest.webservices.restfulwebservices.model.Thread;
import com.sept.rest.webservices.restfulwebservices.model.User;

public interface ThreadRepository extends JpaRepository<Thread, Long> {
	
}