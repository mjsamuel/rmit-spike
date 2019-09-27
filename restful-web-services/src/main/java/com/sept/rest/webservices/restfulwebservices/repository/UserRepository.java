package com.sept.rest.webservices.restfulwebservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sept.rest.webservices.restfulwebservices.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
