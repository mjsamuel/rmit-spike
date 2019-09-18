package com.sept.rest.webservices.restfulwebservices.thread;

import com.sept.rest.webservices.restfulwebservices.thread.ThreadRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.management.InvalidAttributeValueException;
import javax.validation.Valid;


@RestController
public class ThreadController {
	
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
        return threadRepository.save(thread);
    }
	
	@DeleteMapping("/api/thread/{id}")
	public ResponseEntity<?> deleteThread(@PathVariable(name = "id") Long id) throws ThreadNotFoundException {
		Thread thread = threadRepository.findById(id)
				.orElseThrow(() -> new ThreadNotFoundException(id));
		threadRepository.delete(thread);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/api/thread/{id}")
    public Thread updateNote(@PathVariable(value = "id") Long id,
                           @Valid @RequestBody Thread threadDetails) throws ThreadNotFoundException, InvalidAttributeValueException {

		Thread thread = threadRepository.findById(id)
                .orElseThrow(() -> new ThreadNotFoundException(id));

		thread.setTitle(threadDetails.getTitle());
        thread.setContent(threadDetails.getContent());
        thread.setDatetime(threadDetails.getDatetime());
        thread.setArchived(threadDetails.isArchived());
        thread.setUpspikes(threadDetails.getUpspikes());
        thread.setDownspikes(threadDetails.getDownspikes());

        Thread updatedBook = threadRepository.save(thread);

        return updatedBook;
    }
}
