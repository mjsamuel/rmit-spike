package com.sept.rest.webservices.restfulwebservices.resource;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import com.sept.rest.webservices.restfulwebservices.model.Thread;
import com.sept.rest.webservices.restfulwebservices.jwt.resource.JwtTokenResponse;
import com.sept.rest.webservices.restfulwebservices.repository.ThreadRepository;
import com.sept.rest.webservices.restfulwebservices.repository.UserRepository;

@CrossOrigin(origins="*")
@RestController
public class WallResource {

	@Autowired
	ThreadRepository threadRepository;
	
	@Autowired
	UserRepository userRepository;

	@GetMapping("/api/user/{user_id}/feed")
	public ResponseEntity<List<Thread>> getWallByUserId(@PathVariable long user_id) {
		List<Thread> wallThreads = new ArrayList<>();
	
		Optional<User> user = userRepository.findById(user_id);
		if (user.isPresent()) {
			List<Long> subscribedChannels = user.get().getSubscribedTo();
			for (Long channelId : subscribedChannels) {
				List<Thread> channelThreads = threadRepository.findAllByPrimaryChannel(channelId);
				wallThreads.addAll(channelThreads);
			}
		}
		
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "MyController");
		return ResponseEntity.ok(wallThreads);
	}	
}
