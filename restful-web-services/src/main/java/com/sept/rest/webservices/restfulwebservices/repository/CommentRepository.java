package com.sept.rest.webservices.restfulwebservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sept.rest.webservices.restfulwebservices.model.Comment;
// import org.springframework.stereotype.Repository;

// @Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	
}