package com.example.miniapibiblioteczne.service;

import com.example.miniapibiblioteczne.dto.BookInfo;

import java.util.List;

public class BookSearchResponse {
    private String message;
    private List<BookInfo> books;
    private String searchCriteria;

    public BookSearchResponse(String message, List<BookInfo> books, String searchCriteria) {
        this.message = message;
        this.books = books;
        this.searchCriteria = searchCriteria;
    }

    // gettery i settery
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public List<BookInfo> getBooks() { return books; }
    public void setBooks(List<BookInfo> books) { this.books = books; }

    public String getSearchCriteria() { return searchCriteria; }
    public void setSearchCriteria(String searchCriteria) { this.searchCriteria = searchCriteria; }
}
