package com.example.miniapibiblioteczne.repository;

import com.example.miniapibiblioteczne.encje.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository <Book, Long> {
    List<Book> findByAuthor(String author);
    List<Book> findByTitle(String title);


}
