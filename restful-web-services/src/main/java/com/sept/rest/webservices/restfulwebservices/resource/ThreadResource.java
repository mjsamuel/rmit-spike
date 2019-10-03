package com.sept.rest.webservices.restfulwebservices.resource;

import com.sept.rest.webservices.restfulwebservices.repository.ChannelRepository;
import com.sept.rest.webservices.restfulwebservices.repository.ThreadRepository;
import com.sept.rest.webservices.restfulwebservices.exception.ThreadNotFoundException;
import com.sept.rest.webservices.restfulwebservices.model.Channel;
import com.sept.rest.webservices.restfulwebservices.model.Thread;
import com.sept.rest.webservices.restfulwebservices.utils.EntityUpdater;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import javax.management.InvalidAttributeValueException;
import javax.validation.Valid;

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
	public ResponseEntity<Thread> getThread(@PathVariable Long id) throws ThreadNotFoundException {
		Thread threads = threadRepository.findById(id).orElseThrow(() -> new ThreadNotFoundException(id));
		return new ResponseEntity<>(threads, HttpStatus.OK);
	}

	@GetMapping ("/api/user/{user_id}/thread")
	public List<Thread> getThreadsByUserId(@PathVariable Long user_id) {
		return threadRepository.findByAuthorId(user_id);
	}

	@GetMapping ("/api/channel/{channel_id}/thread")
	public List<Thread> getThreadsByChannelId(@PathVariable Long channel_id) {
		return threadRepository.findByChannelId(channel_id);
	}

	@PostMapping("/api/thread")
    public ResponseEntity<Thread> createThread(@RequestBody Thread thread) {
		//threadRepository.save(new Thread(new Long(1), "Title", "Content", false, 0, 0, "Luke Morris"));
//		thread.setDatetime(new Date().toString()); // should this happen from front or backend?
		ResponseEntity<Thread> retVal = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		Optional<Channel> updatedChannel = channelRepository.findById(thread.getChannelId());
		if (updatedChannel.isPresent()
				&& thread.getTitle() != null
				&& !thread.getTitle().equals("")
				&& thread.getContent() != null
				&& !thread.getContent().equals("")
				) {
			updatedChannel.get().addThread(thread);
			threadRepository.save(thread);
			channelRepository.save(updatedChannel.get());
			retVal = new ResponseEntity<>(thread, HttpStatus.CREATED);
		}
		
        return retVal;
    }

	@DeleteMapping("/api/thread/{id}")
	public ResponseEntity<?> deleteThread(@PathVariable(name = "id") Long id) throws ThreadNotFoundException {
		// Should probably not throw an exception, instead return relevant HTTP status code - Sam 29/09/19
		Thread thread = threadRepository.findById(id)
				.orElseThrow(() -> new ThreadNotFoundException(id));
		Optional<Channel> primaryChannel = channelRepository.findById(thread.getChannelId());

		if (primaryChannel.isPresent()) {
			primaryChannel.get().removeThread(thread);
			threadRepository.delete(thread);
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping("/api/thread/{thread_id}")
    public ResponseEntity<Thread> updateNote(@PathVariable Long thread_id,
                           @Valid @RequestBody Thread thread) throws ThreadNotFoundException, InvalidAttributeValueException {

		// This will handle partial update strings, so only updated parameters need to be sent
		Thread existing = threadRepository.findById(thread_id).get();
		EntityUpdater.copyNonNullProperties(thread, existing);
		Thread updated = threadRepository.save(existing);

		return new ResponseEntity<Thread>(updated, HttpStatus.OK);
    }
}
