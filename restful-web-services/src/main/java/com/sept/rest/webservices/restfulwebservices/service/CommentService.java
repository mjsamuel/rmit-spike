package com.sept.rest.webservices.restfulwebservices.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import com.sept.rest.webservices.restfulwebservices.model.Comment;
// import javax.persistence.EntityManagerFactory;
// import javax.persistence.EntityManager;
// import javax.persistence.Persistence;

@Service
public class CommentService {

	private static long idCounter = 0;
	// private EntityManagerFactory emf = Persistence.createEntityManagerFactory();
	// private EntityManager em = emf.createEntityManager();

	public List<Comment> getCommentsByTID(int thread_id) {
		// get all comments by thread
		return null;
	}

	public List<Comment> getCommentsByUID(int user_id) {
		// get all comments for a user
		return null;
	}

	public boolean save(Comment comment) {
		// save a comment to database
		return false;
	}
}