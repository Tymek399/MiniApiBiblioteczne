package com.example.miniapibiblioteczne.dto;

public class BookInfo {
    private String title;
    private String author;
    private boolean available;

    public BookInfo(String title, String author, boolean available) {
        this.title = title;
        this.author = author;
        this.available = available;
    }

    // Gettery i settery

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
