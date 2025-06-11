package com.example.miniapibiblioteczne.dto;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BorrowingDto {
    private String userName;
    private BookDto book;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
}

