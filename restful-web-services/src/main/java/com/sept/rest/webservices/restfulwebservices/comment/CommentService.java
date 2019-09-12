package com.sept.rest.webservices.restfulwebservices.comment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CommentService {

	private static long idCounter = 0;


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