package com.example.miniapibiblioteczne.dto;

import com.example.miniapibiblioteczne.encje.Borrowing;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowingDto {
    private String userName;
    private BookDto book;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    public static BorrowingDto fromEntity(Borrowing borrowing) {
        BorrowingDto dto = new BorrowingDto();
        dto.setUserName(borrowing.getUser().getUserName());
        dto.setBook(BookDto.fromEntity(borrowing.getBook()));
        dto.setBorrowDate(borrowing.getBorrowDate());
        dto.setDueDate(borrowing.getDueDate());
        dto.setReturnDate(borrowing.getReturnDate());
        return dto;
    }
}
