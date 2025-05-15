package com.example.miniapibiblioteczne.repository;

import com.example.miniapibiblioteczne.model.Book;
import com.example.miniapibiblioteczne.model.BookCopy;
import com.example.miniapibiblioteczne.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookCopyRepository extends JpaRepository<BookCopy, Long> {
    Optional<BookCopy> findFirstByBookIdAndStatus(Long bookId, Status status);
    List<BookCopy> findByBook(Book book);
    long countByBookAndStatus(Book book, Status status);

}
