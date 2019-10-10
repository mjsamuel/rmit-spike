package com.sept.rest.webservices.restfulwebservices.model;

public class ChatMessage {

    private String name;
    private String content;

    public ChatMessage() {
    }

    public ChatMessage(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return this.name;
    }

    public String getContent() {
        return this.content;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
