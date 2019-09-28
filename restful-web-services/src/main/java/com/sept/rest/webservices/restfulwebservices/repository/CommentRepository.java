package com.sept.rest.webservices.restfulwebservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sept.rest.webservices.restfulwebservices.model.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	List<Comment> findByThreadId(long thread_id);
}