package com.example.miniapibiblioteczne.repository;

import com.example.miniapibiblioteczne.model.Book;
import com.example.miniapibiblioteczne.model.BookCopy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository <Book, Long> {
    List<Book> findByAuthor(String author);
    List<Book> findByTitle(String title);


}
