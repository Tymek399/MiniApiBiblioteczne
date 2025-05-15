package com.example.miniapibiblioteczne.dto;

public class BookInfo {
    private String title;
    private String author;
    private long copiesAvailable;

    public BookInfo(String title, String author, long copiesAvailable) {
        this.title = title;
        this.author = author;
        this.copiesAvailable = copiesAvailable;
    }


    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public long getCopiesAvailable() { return copiesAvailable; }
    public void setCopiesAvailable(long copiesAvailable) { this.copiesAvailable = copiesAvailable; }
}
