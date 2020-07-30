package com.epam.rd.izh.entity;

import java.io.File;
import java.util.UUID;

public class AddedBook {
    private String title;
    private String author;
    private String genre;
    private String year;
    private UUID uuid;
    private String urlImg;

    private File file;

    public AddedBook(String title, String author, String genre, String year, UUID uuid) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.genre = genre;
        this.uuid = uuid;
        this.urlImg = "/books/" + author.charAt(0) + "/" + author + "/" + title + "/image.jpg";
    }


    public void setFile(File file) {
        this.file = file;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public File getFile() {
        return file;
    }

    public AddedBook() {
        this.title = null;
        this.author = null;
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
                ", year='" + year + '\'' +
                ", genre='" + genre + '\'' +
                "}";
    }
}
