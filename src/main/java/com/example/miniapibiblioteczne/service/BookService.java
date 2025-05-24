package com.example.miniapibiblioteczne.service;

import com.example.miniapibiblioteczne.dto.BookDto;
import com.example.miniapibiblioteczne.encje.Book;
import com.example.miniapibiblioteczne.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookDto addBook(BookDto bookDto) {
        Book book = BookDto.toEntity(bookDto);
        Book saved = bookRepository.save(book);
        return BookDto.fromEntity(saved);
    }

    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(BookDto::fromEntity)
                .toList();
    }

    public Optional<BookDto> getBookByBarcode(String barcode) {
        return bookRepository.findByBarcode(barcode)
                .map(BookDto::fromEntity);
    }

    public ResponseEntity<Void> deleteBookByBarcode(String barcode) {
        Book book = bookRepository.findByBarcode(barcode)
                .orElseThrow(() -> new EntityNotFoundException("Book with ISBN " + barcode + " not found"));
        bookRepository.delete(book);
        return null;
    }

    public List<BookDto> getBooksByAuthor(String author) {
        List<Book> books = bookRepository.findByAuthor(author);
        return books.stream()
                .map(BookDto::fromEntity)
                .toList();
    }

    public List<BookDto> getBooksByTitle(String title) {
        List<Book> books = bookRepository.findByTitle(title);
        return books.stream()
                .map(BookDto::fromEntity)
                .toList();
    }

    public List<BookDto> searchBooks(String title, String author) {
        if (title != null && !title.isBlank()) {
            return getBooksByTitle(title);
        }
        if (author != null && !author.isBlank()) {
            return getBooksByAuthor(author);
        }
        throw new IllegalArgumentException("Search title or author not found");
    }
    public Optional<BookDto> partialUpdateBook(String isbn, BookDto bookDto) {
        return bookRepository.findByIsbn(isbn).map(book -> {
            book.setTitle(bookDto.getTitle());
            book.setAuthor(bookDto.getAuthor());
            book.setPublicationYear(bookDto.getPublicationYear());
            bookRepository.save(book);

            BookDto updatedDto = new BookDto();
            updatedDto.setIsbn(book.getIsbn());
            updatedDto.setTitle(book.getTitle());
            updatedDto.setAuthor(book.getAuthor());
            updatedDto.setPublicationYear(book.getPublicationYear());

            return updatedDto;
        });
    }

}
