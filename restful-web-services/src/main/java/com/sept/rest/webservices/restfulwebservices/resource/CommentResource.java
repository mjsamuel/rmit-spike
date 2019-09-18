package com.sept.rest.webservices.restfulwebservices.resource;

import java.net.URI;
import java.util.List;

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


/**
	TODO: Rename to CommentResource
 */
@CrossOrigin(origins="http://localhost:4200")
@RestController
public class CommentResource {

	@Autowired
	CommentRepository commentRepository;

	@PostMapping("/api/thread/{thread_id}/comment")
	public ResponseEntity<String> createComment(
			@PathVariable String thread_id, @RequestBody String comment){
		
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "MyController");
		return new ResponseEntity<String>(comment, headers, HttpStatus.OK);
	}

	@PostMapping("/api/comment")
	public List<Comment> persist(@RequestBody final Comment comment) {
		commentRepository.save(comment);
		return commentRepository.findAll();
	}


	@GetMapping("/api/comment")
	public List<Comment> getAll() {
		return commentRepository.findAll();
	}
		
}
