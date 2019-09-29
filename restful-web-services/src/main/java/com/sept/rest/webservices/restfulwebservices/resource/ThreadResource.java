package com.sept.rest.webservices.restfulwebservices.resource;

import com.sept.rest.webservices.restfulwebservices.repository.ThreadRepository;
import com.sept.rest.webservices.restfulwebservices.exception.ThreadNotFoundException;
import com.sept.rest.webservices.restfulwebservices.model.Thread;
import com.sept.rest.webservices.restfulwebservices.utils.EntityUpdater;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.management.InvalidAttributeValueException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

@CrossOrigin(origins="*")
@RestController
public class ThreadResource {
	
	@Autowired
	ThreadRepository threadRepository;
	
	@GetMapping ("/api/thread")
	public List<Thread> getAllThreads() {
		return threadRepository.findAll();
	}
	
	@GetMapping ("/api/thread/{id}")
	public Thread getThread(@PathVariable(name = "id") Long id) throws ThreadNotFoundException {
		return threadRepository.findById(id)
				.orElseThrow(() -> new ThreadNotFoundException(id));
	}
	
	@PostMapping("/api/thread")
    public Thread createThread(@RequestBody Thread thread) {
		//threadRepository.save(new Thread(new Long(1), "Title", "Content", false, 0, 0, "Luke Morris"));
//		thread.setDatetime(new Date().toString()); // should this happen from front or backend?
        return threadRepository.save(thread);
    }
	
	@DeleteMapping("/api/thread/{id}")
	public ResponseEntity<?> deleteThread(@PathVariable(name = "id") Long id) throws ThreadNotFoundException {
		// Should probably not throw an exception, instead return relevant HTTP status code - Sam 29/09/19
		Thread thread = threadRepository.findById(id)
				.orElseThrow(() -> new ThreadNotFoundException(id));
		threadRepository.delete(thread);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/api/thread/{thread_id}")
    public ResponseEntity<Thread> updateNote(@PathVariable Long thread_id,
                           @Valid @RequestBody Thread thread) throws ThreadNotFoundException, InvalidAttributeValueException {

		// This will handle partial update strings, so only updated parameters need to be sent
		Thread existing = threadRepository.findById(thread_id).get();
		EntityUpdater.copyNonNullProperties(thread, existing);
		Thread updated = threadRepository.save(existing);

		return new ResponseEntity<Thread>(updated, HttpStatus.OK);

//		Thread thread = threadRepository.findById(id)
//                .orElseThrow(() -> new ThreadNotFoundException(id));
//
//		thread.setTitle(threadDetails.getTitle());
//        thread.setContent(threadDetails.getContent());
//        thread.setDatetime(threadDetails.getDatetime());
//        thread.setArchived(threadDetails.isArchived());
//        thread.setUpspikes(threadDetails.getUpspikes());
//        thread.setDownspikes(threadDetails.getDownspikes());
//
//        Thread updatedBook = threadRepository.save(thread);
//
//        return updatedBook;
    }
}
