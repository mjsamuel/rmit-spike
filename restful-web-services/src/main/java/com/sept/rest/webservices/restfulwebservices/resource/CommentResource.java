package com.sept.rest.webservices.restfulwebservices.resource;

import java.net.URI;
import java.util.List;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sept.rest.webservices.restfulwebservices.model.Comment;
import com.sept.rest.webservices.restfulwebservices.repository.CommentRepository;
import com.sept.rest.webservices.restfulwebservices.service.CommentService;


@CrossOrigin(origins="http://localhost:4200")
@RestController
public class CommentResource {

	// @Autowired
	// private CommentService commentService;

	@Autowired
	private CommentRepository commentRepository;


	@PostMapping("/api/thread/{thread_id}/comment")
	public ResponseEntity<String> persist(@PathVariable long thread_id, @RequestBody final Comment comment) {
		if (comment != null) {
			comment.setThreadId(thread_id);
		}
		// Date date = new Date();
		// System.out.println("Now: " + date.getTime());
		Comment createdComment = commentRepository.save(comment);
		System.out.println("Created comment: " + createdComment);
		if (createdComment == null){
			return ResponseEntity.noContent().build();
		}

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(createdComment.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}


	@GetMapping("/api/thread/{thread_id}/comment")
	public List<Comment> getByThreadId(@PathVariable long thread_id) {
		return commentRepository.findByThreadId(thread_id);
	}

	@GetMapping("/api/comment/{comment_id}")
	public Comment getByCommentId(@PathVariable long comment_id) {
		return commentRepository.findById(comment_id).get();
	}
	
}
