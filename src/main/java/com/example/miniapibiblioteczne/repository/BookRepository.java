package com.example.miniapibiblioteczne.repository;

import com.example.miniapibiblioteczne.encje.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository <Book, Long> {
    List<Book> findByAuthor(String author);
    List<Book> findByTitle(String title);
    Optional<Book> findByIsbn(String isbn);

    Optional<Book> findByBarcode(String barcode);
}
