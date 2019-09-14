package com.sept.rest.webservices.restfulwebservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sept.rest.webservices.restfulwebservices.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	
}