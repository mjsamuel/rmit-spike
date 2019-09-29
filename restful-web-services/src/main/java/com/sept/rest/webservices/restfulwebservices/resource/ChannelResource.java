package com.sept.rest.webservices.restfulwebservices.resource;

import java.util.List;

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
import com.sept.rest.webservices.restfulwebservices.model.Channel;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ChannelResource {

	@Autowired
	ChannelRepository channelRepository;

	@GetMapping("/api/channel")
	public List<Channel> getAll() {
		return channelRepository.findAll();
	}

	@GetMapping("/api/channel/{channel_id}")
	public Channel getByChannelId(@PathVariable long channel_id) {
		return channelRepository.findById(channel_id).get();
	}

	@DeleteMapping("/api/channel/{channel_id}")
	public ResponseEntity<Void> deleteChannel(@PathVariable long channel_id) {
		channelRepository.deleteById(channel_id);

		return ResponseEntity.ok().build();
	}

	@PostMapping("/api/channel")
	public List<Channel> persist(@RequestBody final Channel channel) {
		channelRepository.save(channel);

		return channelRepository.findAll();
	}

	@PutMapping("/api/channel/{channel_id}")
	public ResponseEntity<Channel> updateChannel(@PathVariable long channel_id, @RequestBody Channel channel) {

		Channel updatedChannel = channelRepository.save(channel);

		return new ResponseEntity<Channel>(updatedChannel, HttpStatus.OK);
	}

}
