package com.example.miniapibiblioteczne.controlers;


import com.example.miniapibiblioteczne.model.Book;
import com.example.miniapibiblioteczne.repository.BookRepository;
import com.example.miniapibiblioteczne.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/books")
public class BookControler {

    private final BookService bookService;
    private final BookRepository bookRepository;

    public BookControler(BookService bookService, BookRepository bookRepository) {
        this.bookService = bookService;
        this.bookRepository = bookRepository;
    }
    //Controler dodwania książek
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody @Valid Book book) {
        return ResponseEntity.ok(bookService.addBook(book));
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return bookService.getBookById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        return ResponseEntity.ok(bookService.updateBook(id, book));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(@RequestParam(required = false) String title,
                                                  @RequestParam(required = false) String author) {
        if (title != null) {
            return ResponseEntity.ok(bookService.getBooksByTitle(title));
        } else if (author != null) {
            return ResponseEntity.ok(bookService.getBooksByAuthor(author));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    public BookRepository getBookRepository() {
        return bookRepository;
    }
}
