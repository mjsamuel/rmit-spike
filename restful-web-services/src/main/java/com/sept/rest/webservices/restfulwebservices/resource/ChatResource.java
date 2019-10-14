package com.sept.rest.webservices.restfulwebservices.resource;

import com.sept.rest.webservices.restfulwebservices.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.util.HtmlUtils;

@CrossOrigin(origins="*")
@Controller
public class ChatResource {

    @MessageMapping("/channel/1")
    @SendTo("/1")
    public ChatMessage broadcast(ChatMessage message) throws Exception {
        String name = HtmlUtils.htmlEscape(message.getName());
        String content = HtmlUtils.htmlEscape(message.getContent());
        return new ChatMessage(name, content);
    }
}
