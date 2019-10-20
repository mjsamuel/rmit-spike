package com.sept.rest.webservices.restfulwebservices.resource;

import com.sept.rest.webservices.restfulwebservices.model.ChatMessage;
import com.sept.rest.webservices.restfulwebservices.model.User;
import com.sept.rest.webservices.restfulwebservices.repository.ChatRepository;
import com.sept.rest.webservices.restfulwebservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="*")
@RestController
@Controller
public class ChatResource {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ChatRepository chatRepository;

    @MessageMapping("/channel/{channel_id}")
    @SendTo("/topic/{channel_id}")
    public ChatMessage broadcast(ChatMessage message, @DestinationVariable Long channel_id) throws Exception {
        System.err.println(message);
        String content = HtmlUtils.htmlEscape(message.getContent());
        Long authorId = message.getAuthorId();
        Optional<User> user = userRepository.findById(authorId);

        ChatMessage response = chatRepository.save(new ChatMessage(
                message.getAuthorId(),
                channel_id,
                user.get().getUsername(),
                content,
                message.getDatetime()
        ));
        return response;
    }

    @GetMapping("/api/channel/{channel_id}/chat")
    public List<ChatMessage> getByChannelId(@PathVariable Long channel_id) {
        return chatRepository.findByChannelId(channel_id);
    }

}
