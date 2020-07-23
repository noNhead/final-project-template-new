package com.epam.rd.izh.entity;

import java.util.UUID;

public class AddedBook {
    private String title;
    private String author;
    private String genre;
    private String year;
    private String imgUrl;
    private UUID uuid;


    public AddedBook(String name, String author, String genre, String year, String imgUrl, UUID uuid) {
        this.title = name;
        this.author = author;
        this.imgUrl = imgUrl;
        this.year = year;
        this.genre = genre;
        this.uuid = uuid;
    }

    public AddedBook() {
        this.title = null;
        this.author = null;
        this.imgUrl = null;
        this.year = null;
        this.genre = null;
        this.uuid = null;
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public UUID getId() {
        return uuid;
    }

    public void setId() {
        this.uuid = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return "AddedBook{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", year='" + year + '\'' +
                ", genre='" + genre + '\'' +
                ", uuid=" + uuid.toString() +
                '}';
    }
}
