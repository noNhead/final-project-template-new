package com.epam.rd.izh.dto;

import java.util.List;

/**
 * Сущность Чат-комнаты
 */
public class Room {

    private Long id;
    private String name;
    private String description;
    private List<Message> messages;

    public Room() {
        super();
    }

    public Room(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
