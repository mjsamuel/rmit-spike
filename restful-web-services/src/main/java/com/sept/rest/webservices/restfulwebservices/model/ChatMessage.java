package com.sept.rest.webservices.restfulwebservices.model;

import org.ocpsoft.prettytime.PrettyTime;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "chat_messages")
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "author_id", nullable = false)
    private Long authorId;
    @Column(name = "channel_id")
    private Long channelId;
    @Column(name = "username")
    private String username;
    @Column(name = "content")
    private String content;
    @Column(name = "datetime")
    private Date datetime;

    public ChatMessage() {
    }

    public ChatMessage(Long id, Long authorId, Long channelId, String content, Date datetime) {
        this.id = id;
        this.authorId = authorId;
        this.content = content;
        this.datetime = datetime;
    }

    public ChatMessage(Long authorId, Long channelId, String username, String content, Date datetime) {
        this.authorId = authorId;
        this.channelId = channelId;
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

    public Long getChannelId() {
        return this.channelId;
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

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
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
        return this.authorId + "," + this.channelId + ", " + this.username + ", " + this.content;
    }
}
