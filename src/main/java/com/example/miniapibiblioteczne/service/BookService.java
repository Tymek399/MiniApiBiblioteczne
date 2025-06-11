package com.example.miniapibiblioteczne.service;

import com.example.miniapibiblioteczne.dto.BookDto;
import com.example.miniapibiblioteczne.encje.Book;
import com.example.miniapibiblioteczne.mapper.BookMapper;
import com.example.miniapibiblioteczne.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookDto addBook(BookDto bookDto) {
        Book book = bookMapper.toEntity(bookDto);
        Book saved = bookRepository.save(book);
        return bookMapper.toDto(saved);
    }

    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(bookMapper::toDto)
                .toList();
    }

    public Optional<BookDto> getBookByBarcode(String barcode) {
        return bookRepository.findByBarcode(barcode)
                .map(bookMapper::toDto);
    }

    public ResponseEntity<Void> deleteBookByBarcode(String barcode) {
        Book book = bookRepository.findByBarcode(barcode)
                .orElseThrow(() -> new EntityNotFoundException("Book with Barcode " + barcode + " not found"));
        bookRepository.delete(book);
        return ResponseEntity.noContent().build();
    }

    public List<BookDto> getBooksByAuthor(String author) {
        List<Book> books = bookRepository.findByAuthor(author);
        return books.stream()
                .map(bookMapper::toDto)
                .toList();
    }

    public List<BookDto> getBooksByTitle(String title) {
        List<Book> books = bookRepository.findByTitle(title);
        return books.stream()
                .map(bookMapper::toDto)
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
            if (bookDto.getTitle() != null) {
                book.setTitle(bookDto.getTitle());
            }
            if (bookDto.getAuthor() != null) {
                book.setAuthor(bookDto.getAuthor());
            }
            if (bookDto.getPublicationYear() != null) {
                book.setPublicationYear(bookDto.getPublicationYear());
            }
            if (bookDto.getBarcode() != null) {
                book.setBarcode(bookDto.getBarcode());
            }
            Book updated = bookRepository.save(book);
            return bookMapper.toDto(updated);
        });
    }
}
