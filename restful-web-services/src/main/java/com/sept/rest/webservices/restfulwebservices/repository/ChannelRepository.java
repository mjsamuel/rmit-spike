package com.sept.rest.webservices.restfulwebservices.repository;

import com.sept.rest.webservices.restfulwebservices.model.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelRepository extends JpaRepository<Channel, Long> {
}