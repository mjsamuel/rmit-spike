package com.sept.rest.webservices.restfulwebservices.repository;

import com.sept.rest.webservices.restfulwebservices.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findByChannelId(Long channel_id);
}
