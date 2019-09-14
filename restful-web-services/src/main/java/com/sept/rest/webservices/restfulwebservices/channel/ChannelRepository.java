package com.sept.rest.webservices.restfulwebservices.channel;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long>{
	List<Channel> findByID(long id);

	List<Channel> deleteByID(long id);
	
	Channel save(@Valid Channel channel);
}