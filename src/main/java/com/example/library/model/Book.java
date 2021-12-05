package com.example.library.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.UUID;

public class Book  {
    private UUID guid;
    private String name;
    private String author;
    private String category;
    private String language;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate publicationDate;
    private String isbn;
    private boolean isTaken;


    public Book(@JsonProperty("guid") UUID guid,
                @JsonProperty("name") String name,
                @JsonProperty("author")String author,
                @JsonProperty("category") String category,
                @JsonProperty("language")String language,
                @JsonProperty("publicationDate") LocalDate publicationDate,
                @JsonProperty("isbn") String isbn,
                @JsonProperty("isTaken") boolean isTaken) {
        this.guid = guid;
        this.name = name;
        this.author = author;
        this.category = category;
        this.language = language;
        this.publicationDate = publicationDate;
        this.isbn = isbn;
this.isTaken=isTaken;
    }

    public boolean isTaken() {
        return isTaken;
    }

    public void setTaken(boolean taken) {
        isTaken = taken;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public UUID getGuid() {
        return guid;
    }

    public void setGuid(UUID guid) {
        this.guid = guid;
    }
}
