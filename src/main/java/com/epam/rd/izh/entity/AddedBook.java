package com.epam.rd.izh.entity;

import java.util.Date;
import java.util.UUID;

public class AddedBook {
    private String name;
    private String Author;
    private String imgUrl;
    private Date date;

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    private String genre;
    private UUID id;

    public AddedBook(String name, String author, String genre, Date date, String imgUrl, UUID id) {
        this.name = name;
        Author = author;
        this.imgUrl = imgUrl;
        this.date = date;
        this.genre = genre;
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
