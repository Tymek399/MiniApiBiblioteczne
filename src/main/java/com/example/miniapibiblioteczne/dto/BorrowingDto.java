package com.example.miniapibiblioteczne.dto;

import com.example.miniapibiblioteczne.model.Borrowing;

import java.time.LocalDate;

public class BorrowingDto {
    private Long id;
    private Long userId;
    private Long bookCopyId;
    private String bookTitle;
    private String author;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    public static BorrowingDto fromEntity(Borrowing borrowing) {
        BorrowingDto dto = new BorrowingDto();
        dto.setId(borrowing.getId());
        dto.setUserId(borrowing.getUser().getId());
        dto.setBookCopyId(borrowing.getBookCopy().getId());
        dto.setBookTitle(borrowing.getBookCopy().getBook().getTitle());
        dto.setBookAuthor(borrowing.getBookCopy().getBook().getAuthor());
        dto.setBorrowDate(borrowing.getBorrowDate());
        dto.setDueDate(borrowing.getDueDate());
        dto.setReturnDate(borrowing.getReturnDate());
        return dto;
    }

    public String getBookAuthor() {
        return author;
    }

    public void setBookAuthor(String author) {
        this.author = author;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getBookCopyId() { return bookCopyId; }
    public void setBookCopyId(Long bookCopyId) { this.bookCopyId = bookCopyId; }

    public String getBookTitle() { return bookTitle; }
    public void setBookTitle(String bookTitle) { this.bookTitle = bookTitle; }

    public LocalDate getBorrowDate() { return borrowDate; }
    public void setBorrowDate(LocalDate borrowDate) { this.borrowDate = borrowDate; }

    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }
}
