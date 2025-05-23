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
import org.springframework.web.bind.annotation.RestController;

import com.sept.rest.webservices.restfulwebservices.model.User;
import com.sept.rest.webservices.restfulwebservices.model.Channel;
import com.sept.rest.webservices.restfulwebservices.model.Thread;
import com.sept.rest.webservices.restfulwebservices.repository.ChannelRepository;
import com.sept.rest.webservices.restfulwebservices.repository.ThreadRepository;
import com.sept.rest.webservices.restfulwebservices.repository.UserRepository;

@CrossOrigin(origins="*")
@RestController
public class WallResource {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ChannelRepository channelRepository;

	@GetMapping("/api/user/{user_id}/wall")
	public ResponseEntity<?> getWallByUserId(@PathVariable long user_id) {
		ResponseEntity<?> retVal = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		List<Thread> wallThreads = new ArrayList<>();
	
		Optional<User> user = userRepository.findById(user_id);
		if (user.isPresent()) {
			List<Long> subscribedChannels = user.get().getSubscribedTo();
			for (Long channelId : subscribedChannels) {
				Optional<Channel> channel = channelRepository.findById(channelId);
				if (channel.isPresent()) {
					List<Thread> channelThreads = channel.get().getThreads();
					wallThreads.addAll(channelThreads);
				}
			}
			
			// Ordering threads from most recent to lest recent
			if (wallThreads != null && wallThreads.size() > 1) {
				Collections.sort(wallThreads, new Comparator<Thread>() {
					public int compare(Thread o1, Thread o2) {
						return o2.getDatetime().compareTo(o1.getDatetime());
					}
				});
			}
			
			HashMap<String, Object> content = new HashMap<>();
			content.put("threads", wallThreads);
			retVal = new ResponseEntity<>(content, HttpStatus.OK);
		}
		
		return retVal;
	}	
}
