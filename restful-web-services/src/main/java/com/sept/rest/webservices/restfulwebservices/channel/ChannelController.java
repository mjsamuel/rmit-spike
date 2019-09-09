package com.sept.rest.webservices.restfulwebservices.channel;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ChannelController {

	@Autowired
	private ChannelRepository channelRepo;

	@GetMapping("/api/channel")
	public List<Channel> getAllChannels() {
		return channelRepo.findAll();
	}

	// Get specific channel
	@GetMapping("/api/channel/{id}")
	public List<Channel> getChannel(@PathVariable(name = "id") long id) {
		return channelRepo.findByID(id);
	}

	// Get specific user in channel
	@GetMapping("/api/channel?user={user_id}")
	public List<Channel> getUserInChannel(@PathVariable long id) {
		return channelRepo.findByID(id);
	}

	// Delete Channel
	@DeleteMapping("/api/channel/{id}")
	public ResponseEntity<Void> deleteChannel(@PathVariable long id) {
		channelRepo.deleteByID(id);

		return ResponseEntity.ok().build();
	}

	// Update specific Channel
	@PutMapping("/api/channel/{id}")
	public ResponseEntity<Channel> updateChannelById(@PathVariable long id, @RequestBody Channel channel) {
		channel.setChannelId(id);
		
		Channel updatedChannel = channelRepo.save(channel);
		
		return new ResponseEntity<Channel>(channel, HttpStatus.OK);
	}

	// Update Channel
	@PostMapping("/api/channel")
	public ResponseEntity<Void> createChannel(@Valid @RequestBody Channel channel) {
		Channel createdChannel = channelRepo.save(channel);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/channel")
				.buildAndExpand(createdChannel.getChannelId()).toUri();

		return ResponseEntity.created(uri).build();
	}

}
