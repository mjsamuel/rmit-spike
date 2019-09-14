package com.sept.rest.webservices.restfulwebservices.repository;

import java.util.List;

import javax.validation.Valid;
import com.sept.rest.webservices.restfulwebservices.model.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelRepository extends JpaRepository<Channel, Long>{
	// List<Channel> findByID(Long id);

	// List<Channel> deleteByID(Long id);
	
	// Channel save(@Valid Channel channel);
}