package com.example.miniapibiblioteczne.repository;

import com.example.miniapibiblioteczne.encje.Book;
import com.example.miniapibiblioteczne.encje.BookCopy;
import com.example.miniapibiblioteczne.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookCopyRepository extends JpaRepository<BookCopy, Long> {
    Optional<BookCopy> findFirstByBookIdAndStatus(Long bookId, Status status);
    List<BookCopy> findByBook(Book book);


}
