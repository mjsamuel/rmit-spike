package com.sept.rest.webservices.restfulwebservices.resource;

import com.sept.rest.webservices.restfulwebservices.repository.ChannelRepository;
import com.sept.rest.webservices.restfulwebservices.repository.ThreadRepository;
import com.sept.rest.webservices.restfulwebservices.exception.ThreadNotFoundException;
import com.sept.rest.webservices.restfulwebservices.model.Channel;
import com.sept.rest.webservices.restfulwebservices.model.Thread;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import javax.management.InvalidAttributeValueException;

@CrossOrigin(origins="*")
@RestController
public class ThreadResource {
	
	@Autowired
	ChannelRepository channelRepository;
	
	@Autowired
	ThreadRepository threadRepository;
	
	@GetMapping ("/api/thread")
	public  ResponseEntity<List<Thread>> getAllThreads() {
		List<Thread> threads = threadRepository.findAll();
		return new ResponseEntity<>(threads, HttpStatus.OK);
	}
	
	@GetMapping ("/api/thread/{id}")
	public ResponseEntity<Thread>  getThread(@PathVariable Long id) throws ThreadNotFoundException {
		Thread threads = threadRepository.findById(id).orElseThrow(() -> new ThreadNotFoundException(id));
		return new ResponseEntity<>(threads, HttpStatus.OK);
	}
	
	@DeleteMapping("/api/thread/{id}")
	public ResponseEntity<?> deleteThread(@PathVariable Long id) throws ThreadNotFoundException {
		Thread thread = threadRepository.findById(id)
				.orElseThrow(() -> new ThreadNotFoundException(id));
		Optional<Channel> primaryChannel = channelRepository.findById(thread.getPrimaryChannel());
		
		if (primaryChannel.isPresent()) {
			primaryChannel.get().removeThread(thread);
			threadRepository.delete(thread);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/api/thread/{id}")
    public ResponseEntity<?> updateNote(@PathVariable Long id,
    		@RequestBody Thread thread) throws ThreadNotFoundException, InvalidAttributeValueException {
		thread.setId(id);
        threadRepository.save(thread);

		return new ResponseEntity<>(HttpStatus.OK);
    }
}
