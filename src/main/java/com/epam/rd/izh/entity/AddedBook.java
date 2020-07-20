package com.epam.rd.izh.entity;

import java.util.Date;
import java.util.UUID;

public class AddedBook {
    private String title;
    private String author;
    private String imgUrl;
    private String year;
    private String genre;
    private UUID id;

    public AddedBook(String name, String author, String genre, String year, String imgUrl) {
        this.title = name;
        this.author = author;
        this.imgUrl = imgUrl;
        this.year = year;
        this.genre = genre;
        this.id = UUID.randomUUID();
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
