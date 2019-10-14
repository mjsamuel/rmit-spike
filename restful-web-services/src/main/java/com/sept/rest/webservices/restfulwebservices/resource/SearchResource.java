package com.sept.rest.webservices.restfulwebservices.resource;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sept.rest.webservices.restfulwebservices.model.User;
import com.sept.rest.webservices.restfulwebservices.model.Channel;
import com.sept.rest.webservices.restfulwebservices.repository.ChannelRepository;
import com.sept.rest.webservices.restfulwebservices.repository.ThreadRepository;
import com.sept.rest.webservices.restfulwebservices.repository.UserRepository;


@CrossOrigin(origins="*")
@RestController
public class SearchResource {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ChannelRepository channelRepository;
	
	@Autowired
	ThreadRepository threadRepository;
	
	@GetMapping("/api/search")
	public ResponseEntity<?> search(@RequestParam String query) {
		ResponseEntity<?> retVal;
		
		if (query.equals("") || query == null) {
			retVal = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			// Kept as lists for now, for when (or if) fuzzy search is implemented
			ArrayList<User> users = userRepository.findByUsernameContainingIgnoreCase(query);
			ArrayList<Channel> channels = channelRepository.findByNameContainingIgnoreCase(query);
			
			HashMap<String, Object> responseBody = new HashMap<>();
			responseBody.put("users", users);
			responseBody.put("channels", channels);
	
			if (users.isEmpty() && channels.isEmpty()) {
				retVal = new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				retVal = new ResponseEntity<>(responseBody, HttpStatus.OK);
			}
		}
		
		return retVal;
	}
}
