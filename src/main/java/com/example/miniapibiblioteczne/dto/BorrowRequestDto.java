package com.example.miniapibiblioteczne.dto;

import javax.validation.constraints.NotNull;

;
public class BorrowRequestDto {

    @NotNull(message = "ID użytkownika nie może być puste")
    private Long userId;

    @NotNull(message = "ID książki nie może być puste")
    private Long bookId;

    public BorrowRequestDto() {
    }

    public BorrowRequestDto(Long userId, Long bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }

    // Gettery i settery

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}
