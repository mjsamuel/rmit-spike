package com.sept.rest.webservices.restfulwebservices.resource;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sept.rest.webservices.restfulwebservices.repository.ChannelRepository;
import com.sept.rest.webservices.restfulwebservices.repository.ThreadRepository;
import com.sept.rest.webservices.restfulwebservices.repository.UserRepository;
import com.sept.rest.webservices.restfulwebservices.model.Channel;
import com.sept.rest.webservices.restfulwebservices.model.Thread;
import com.sept.rest.webservices.restfulwebservices.model.User;


@CrossOrigin(origins = "*")
@RestController
public class ChannelResource {

	@Autowired
	ChannelRepository channelRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ThreadRepository threadRepository;

	/**
	 * Get all channels
	 */
	@GetMapping("/api/channel")
	public List<Channel> getAll() {
		return channelRepository.findAll();
	}
	
	/**
	 * Returns the channel name, whether the user is subscribed to the channels as well as 
	 * all threads from the specified channel.
	 */
	@GetMapping("/api/channel/{channel_id}")
	public ResponseEntity<?> getByChannelId(@PathVariable long channel_id, @RequestBody Long user_id) {
		ResponseEntity<?> retVal = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		Optional<Channel> channel = channelRepository.findById(channel_id);
		Optional<User> user = userRepository.findById(user_id);

		if (channel.isPresent() && user.isPresent()) {
			List<Thread> channelThreads = channel.get().getThreads();
			boolean subscribed = user.get().isSubscribedTo(channel_id);
			
			HashMap<String, Object> responseBody = new HashMap<>();
			responseBody.put("channelName", channel.get().getName());
			responseBody.put("threads", channelThreads);
			responseBody.put("subscribed", subscribed);

			retVal = new ResponseEntity<>(responseBody, HttpStatus.OK);
		}

		return retVal;	
	}

	/**
	 * Deletes a channel
	 */
	@DeleteMapping("/api/channel/{channel_id}")
	public ResponseEntity<Void> deleteChannel(@PathVariable Long channel_id) {
		channelRepository.deleteById(channel_id);

		return ResponseEntity.ok().build();
	}
	
	/**
	 * Creates a new channel
	 */
	@PostMapping("/api/channel")
	public List<Channel> persist(@RequestBody final Channel channel) {
		channel.setDatetime(new Date().toString());
		channelRepository.save(channel);

		return channelRepository.findAll();
	}
	
	/**
	 * Updates a channel
	 */
	@PutMapping("/api/channel/{channel_id}")
	public ResponseEntity<Channel> updateChannel(@PathVariable long channel_id, @RequestBody Channel channel) {

		Channel updatedChannel = channelRepository.save(channel);

		return new ResponseEntity<Channel>(updatedChannel, HttpStatus.OK);
	}
	
	/**
	 * Creates a new thread within the channel updating both the 'channels' and 'threads' table
	 */
	@PutMapping("/api/channel/{channel_id}/new-thread")
	public ResponseEntity<Channel> postThreadToChannel(@PathVariable long channel_id, @RequestBody Thread thread) {
		Optional<Channel> updatedChannel = channelRepository.findById(channel_id);
		if (updatedChannel.isPresent()
				&& channel_id == thread.getPrimaryChannel()
				&& thread.getTitle() != null
				&& !thread.getTitle().equals("")
				&& thread.getContent() != null
				&& !thread.getContent().equals("")) {
			updatedChannel.get().addThread(thread);
			thread.setDatetime(new Date().toString());
			threadRepository.save(thread);
			channelRepository.save(updatedChannel.get());
		}

		return new ResponseEntity<Channel>(HttpStatus.OK);
	}
	
}
