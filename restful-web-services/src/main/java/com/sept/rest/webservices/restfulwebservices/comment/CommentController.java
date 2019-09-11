package com.sept.rest.webservices.restfulwebservices.comment;

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


@CrossOrigin(origins="http://localhost:4200")
@RestController
public class CommentController {

	@PostMapping("/api/thread/{thread_id}/comment")
	public ResponseEntity<String> createComment(
			@PathVariable String thread_id, @RequestBody String comment){
		
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "MyController");
		return new ResponseEntity<String>(comment, headers, HttpStatus.OK);

	}
		
}
