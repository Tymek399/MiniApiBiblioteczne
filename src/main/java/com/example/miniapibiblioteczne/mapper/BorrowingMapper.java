package com.example.miniapibiblioteczne.mapper;

import com.example.miniapibiblioteczne.dto.BorrowingDto;
import com.example.miniapibiblioteczne.encje.Borrowing;
import org.mapstruct.factory.Mappers;

// tu masz builder

public class BorrowingMapper {
    private static final BookMapper bookMapper = Mappers.getMapper(BookMapper.class);

    public static BorrowingDto fromEntity(Borrowing borrowing) {
        if (borrowing == null) return null;

        return BorrowingDto.builder()
                .userName(borrowing.getUser().getUserName())
                .book(bookMapper.toDto(borrowing.getBook()))
                .borrowDate(borrowing.getBorrowDate())
                .dueDate(borrowing.getDueDate())
                .returnDate(borrowing.getReturnDate())
                .build();
    }

}
