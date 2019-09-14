package com.sept.rest.webservices.restfulwebservices.thread;

import com.sept.rest.webservices.restfulwebservices.thread.Thread;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThreadRepository extends JpaRepository<Thread, Long>{
}
