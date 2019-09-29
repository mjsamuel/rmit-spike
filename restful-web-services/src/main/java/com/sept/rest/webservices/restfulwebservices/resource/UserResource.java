package com.sept.rest.webservices.restfulwebservices.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
import com.sept.rest.webservices.restfulwebservices.jwt.JwtTokenUtil;
import com.sept.rest.webservices.restfulwebservices.jwt.resource.JwtTokenResponse;
import com.sept.rest.webservices.restfulwebservices.model.User;

@CrossOrigin(origins = "*")
@RestController
public class UserResource {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@GetMapping("/api/user")
	public ResponseEntity<List<User>> getAll() {
		List<User> users = userRepository.findAll();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@GetMapping("/api/user/{user_id}")
	public ResponseEntity<User> getByUserId(@PathVariable long user_id) {
		User user = userRepository.findById(user_id).get();
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@DeleteMapping("/api/user/{user_id}")
	public ResponseEntity<?> deleteUser(@PathVariable long user_id) {
		userRepository.deleteById(user_id);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/api/user")
	public ResponseEntity<?> registerUser(@RequestBody final User user) {
		ResponseEntity<?> retVal = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		User existingUsername = userRepository.findByUsername(user.getUsername());
		User existingEmail = userRepository.findByEmail(user.getEmail());
		
		if(existingUsername == null 
				&& existingEmail == null
				&& !user.getEmail().equals("")
				&& user.getEmail() != null
				&& !user.getUsername().equals("")
				&& user.getUsername() != null
				&& !user.getPassword().equals("")
				&& user.getPassword() != null
				&& !user.getFirstName().equals("")
				&& user.getFirstName() != null
				&& !user.getLastName().equals("")
				&& user.getLastName() != null) {
			String encryptedPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(encryptedPassword);
			userRepository.save(user);
			
			final UserDetails userDetails = jwtInMemoryUserDetailsService.loadUserByUsername(user.getUsername());
			final String token = jwtTokenUtil.generateToken(userDetails);
			retVal = ResponseEntity.ok(new JwtTokenResponse(token));
		}

		return retVal;
	}

	@PutMapping("/api/user/{user_id}")
	public ResponseEntity<User> updateUser(@PathVariable long user_id, @RequestBody User user) {
		user.setId(user_id);
		User updatedUser = userRepository.save(user);

		return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
	}
}
