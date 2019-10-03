package com.sept.rest.webservices.restfulwebservices.resource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sept.rest.webservices.restfulwebservices.model.User;
import com.sept.rest.webservices.restfulwebservices.model.Channel;
import com.sept.rest.webservices.restfulwebservices.model.Thread;
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
		
		
		// Kept as lists for now, for when (or if) fuzzy search is implemented
		ArrayList<User> users = new ArrayList<>();
		User user = userRepository.findByUsername(query);
		if (user != null) users.add(user);
		
		ArrayList<Channel> channels = new ArrayList<>();
		Channel channel = channelRepository.findByName(query);
		if (channel != null) channels.add(channel);
		
		HashMap<String, Object> responseBody = new HashMap<>();
		responseBody.put("users", users);
		responseBody.put("channels", channels);
		
		ResponseEntity<?> retVal = new ResponseEntity<>(responseBody, HttpStatus.OK);
		
		return retVal;
	}
}
