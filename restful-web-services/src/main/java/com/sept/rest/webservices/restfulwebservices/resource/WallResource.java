package com.sept.rest.webservices.restfulwebservices.resource;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RestController;

import com.sept.rest.webservices.restfulwebservices.model.User;
import com.sept.rest.webservices.restfulwebservices.model.Channel;
import com.sept.rest.webservices.restfulwebservices.model.Thread;
import com.sept.rest.webservices.restfulwebservices.repository.ThreadRepository;
import com.sept.rest.webservices.restfulwebservices.repository.UserRepository;

@CrossOrigin(origins="*")
@RestController
public class WallResource {
	
	@Autowired
	UserRepository userRepository;

	@GetMapping("/api/user/{user_id}/feed")
	public ResponseEntity<?> getWallByUserId(@PathVariable long user_id) {
		List<Thread> wallThreads = new ArrayList<>();
	
		Optional<User> user = userRepository.findById(user_id);
		if (user.isPresent()) {
			List<Channel> subscribedChannels = user.get().getSubscribedTo();
			for (Channel channel : subscribedChannels) {
				List<Thread> channelThreads = channel.getThreads();
				wallThreads.addAll(channelThreads);
			}
		}
		
		HashMap<String, Object> content = new HashMap<>();
		content.put("threads", wallThreads);
		
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "MyController");
		return new ResponseEntity<>(content, HttpStatus.OK);
	}	
}
