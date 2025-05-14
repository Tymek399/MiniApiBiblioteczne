package com.example.miniapibiblioteczne.repository;

import com.example.miniapibiblioteczne.model.BookCopy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookCopyRepository extends JpaRepository<BookCopy ,Long> {
    List<BookCopy> findBookCopyByStatusAndBookId(String status, Long bookId);

    List<BookCopy> findBookCopyById( Long BookId);
}
