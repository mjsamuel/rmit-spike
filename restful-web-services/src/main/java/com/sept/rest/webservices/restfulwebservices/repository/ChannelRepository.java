package com.sept.rest.webservices.restfulwebservices.repository;

import com.sept.rest.webservices.restfulwebservices.model.Channel;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelRepository extends JpaRepository<Channel, Long> {
	
	Channel findByName(String name);
	
	ArrayList<Channel> findByNameContainingIgnoreCase(String name);

}