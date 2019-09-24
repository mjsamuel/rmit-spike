package com.sept.rest.webservices.restfulwebservices.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sept.rest.webservices.restfulwebservices.model.User;
import com.sept.rest.webservices.restfulwebservices.repository.ChannelRepository;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class WallResource {

	@Autowired
	ChannelRepository channelRepository;
//	UserRepository userRepository;

	@GetMapping("/api/user/{user_id}/feed")
	public ResponseEntity<String> getWallByUserId(@PathVariable long user_id) {
		/*
		User user = userRepository.findByUserId(user_id);
		List subscribedChannels = user.getSubscribedChannels();
		for (long channelId : subscribedChannels) {
		}
		*/
		
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "MyController");
		return new ResponseEntity<String>(headers, HttpStatus.OK);
	}	
}
