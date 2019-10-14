package com.sept.rest.webservices.restfulwebservices.resource;

import com.sept.rest.webservices.restfulwebservices.model.ChatMessage;
import com.sept.rest.webservices.restfulwebservices.model.User;
import com.sept.rest.webservices.restfulwebservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.util.HtmlUtils;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="*")
@Controller
public class ChatResource {
    @Autowired
    UserRepository userRepository;

    @MessageMapping("/channel/{channelId}")
    @SendTo("/topic/{channelId}")
    public ChatMessage broadcast(ChatMessage message, @DestinationVariable Long channelId) throws Exception {
        System.err.println(message);
        String content = HtmlUtils.htmlEscape(message.getContent());
        Long authorId = message.getAuthorId();
        Optional<User> user = userRepository.findById(authorId);
        return new ChatMessage(message.getAuthorId(), user.get().getUsername(), content, message.getDatetime());
    }
}
