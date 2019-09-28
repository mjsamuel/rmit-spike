package com.sept.rest.webservices.restfulwebservices.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.sept.rest.webservices.restfulwebservices.repository.UserRepository;
import com.sept.rest.webservices.restfulwebservices.exception.ExistingUserException;
import com.sept.rest.webservices.restfulwebservices.model.User;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserResource {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/api/user")
	public List<User> getAll() {
		return userRepository.findAll();
	}

	@GetMapping("/api/user/{user_id}")
	public User getByUserId(@PathVariable long user_id) {
		return userRepository.findById(user_id).get();
	}

	@DeleteMapping("/api/user/{user_id}")
	public ResponseEntity<Void> deleteUser(@PathVariable long user_id) {
		userRepository.deleteById(user_id);

		return ResponseEntity.ok().build();
	}

	@PostMapping("/api/user")
	public ResponseEntity<?> registerUser(@RequestBody final User user) throws ExistingUserException {
		User existingUsername = userRepository.findByUsername(user.getUsername());
		User existingEmail = userRepository.findByEmail(user.getEmail());
		
		if(existingUsername == null 
				&& existingEmail == null
				&& !user.getPassword().equals("")) {
			String encryptedPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(encryptedPassword);
			userRepository.save(user);
		} else {
			throw new ExistingUserException();
		}

		return null;
	}

	@PutMapping("/api/user/{user_id}")
	public ResponseEntity<User> updateUser(@PathVariable long user_id, @RequestBody User user) {

		User updatedUser = userRepository.save(user);

		return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
	}
}
