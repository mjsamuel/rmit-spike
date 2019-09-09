package com.sept.rest.webservices.restfulwebservices.thread;

import com.sept.rest.webservices.restfulwebservices.thread.ThreadRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;
import javax.validation.Valid;


@RestController
public class ThreadController {
	
	ThreadRepository threadRepository;
	
	@GetMapping ("/api/thread")
	public List<Thread> getAllThreads() {
		return threadRepository.findAll();
	}
	
	@PostMapping("/api/thread")
    public Thread createThread(@Valid @RequestBody Thread thread) {
        return threadRepository.save(thread);
    }
	
	@GetMapping ("/api/thread/{id}")
	public Thread getThread(@PathVariable(name = "id") Long id) throws ThreadNotFoundException {
		return threadRepository.findById(id)
				.orElseThrow(() -> new ThreadNotFoundException(id));
	}
	
	@DeleteMapping("api/thread/{id}")
	public ResponseEntity<?> deleteThread(@PathVariable(name = "id") Long id) throws ThreadNotFoundException {
		Thread thread = threadRepository.findById(id)
				.orElseThrow(() -> new ThreadNotFoundException(id));
		threadRepository.delete(thread);
		return ResponseEntity.ok().build();
	}
}
