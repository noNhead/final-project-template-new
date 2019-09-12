package com.epam.rd.izh.dto;

import java.time.LocalDateTime;

public class Message {

    private Long id;
    private User author;
    private String message;
    private LocalDateTime creationTime;

    public Message() {}

    public Message(User author, String message, LocalDateTime creationTime) {
        this.author = author;
        this.message = message;
        this.creationTime = creationTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }
}
