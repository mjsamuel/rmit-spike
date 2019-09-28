package com.sept.rest.webservices.restfulwebservices.jwt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.management.InvalidAttributeValueException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sept.rest.webservices.restfulwebservices.model.User;
                                                                      
@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {
	  
  static List<User> inMemoryUserList = new ArrayList<User>();
  
  static {
    try {
    	User user1 = new User(1L, "s1234567@student.rmit.edu.au", "sept",
    			"$2a$10$3zHzb.Npv1hfZbLEU5qsdOju/tk2je6W6PnNnY.c1ujWPcZh4PL6e", "john", "doe");
    	User user2 = new User(2L, "s7654321@student.rmit.edu.au", "user",
	            "$2a$10$r/45rWG0VCwvjrOeVkhqWucO7zmnj7TUzZWMQFH6.h12AqSLteyN2", "jane", "doe");
//    	userRepository.save(user1);
//    	userRepository.save(user2);
		inMemoryUserList.add(user1);
	    inMemoryUserList.add(user2);
	} catch (InvalidAttributeValueException e) {
		e.printStackTrace();
	}
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> findFirst = inMemoryUserList.stream()
        .filter(user -> user.getUsername().equals(username)).findFirst();

    if (!findFirst.isPresent()) {
      throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
    }

    return findFirst.get();
  }

}


