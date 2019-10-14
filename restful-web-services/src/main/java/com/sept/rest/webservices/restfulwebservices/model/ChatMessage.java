package com.sept.rest.webservices.restfulwebservices.model;

import org.ocpsoft.prettytime.PrettyTime;

import java.util.Date;

public class ChatMessage {

    private Long id;
    private Long authorId;
    private String username;
    private String content;
    private Date datetime;

    public ChatMessage() {
    }
    public ChatMessage(Long id, Long authorId, String content, Date datetime) {
        this.id = id;
        this.authorId = authorId;
        this.content = content;
        this.datetime = datetime;
    }

    public ChatMessage(Long authorId, String username, String content, Date datetime) {
        this.authorId = authorId;
        this.username = username;
        this.content = content;
        this.datetime = datetime;
    }

    public Long getId() {
        return this.id;
    }

    public Long getAuthorId() {
        return this.authorId;
    }

    public String getUsername() {
        return this.username;
    }

    public String getContent() {
        return this.content;
    }

    public Date getDatetime() {
        return this.datetime;
    }

    public String getTimeDelta() {
        PrettyTime p = new PrettyTime(new Date());
        return p.format(this.datetime);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }


    @Override
    public String toString() {
        return this.authorId + "," + this.username + ", " + this.content;
    }
}
