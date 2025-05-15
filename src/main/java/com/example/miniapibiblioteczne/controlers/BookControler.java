package com.example.miniapibiblioteczne.controlers;

import com.example.miniapibiblioteczne.dto.BookInfo;
import com.example.miniapibiblioteczne.encje.Book;
import com.example.miniapibiblioteczne.repository.BookRepository;
import com.example.miniapibiblioteczne.service.BookService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookControler {

    private final BookService bookService;
    private final BookRepository bookRepository;
    private final ObjectMapper objectMapper;

    public BookControler(BookService bookService, BookRepository bookRepository, ObjectMapper objectMapper) {
        this.bookService = bookService;
        this.bookRepository = bookRepository;
        this.objectMapper = objectMapper;
    }

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

    @PatchMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody JsonMergePatch patch) {
        try {
            Optional<Book> existingBook = bookService.getBookById(id);
            if (existingBook.isPresent()) {
                Book patchedBook = applyPatch(patch, existingBook.get());
                Book savedBook = bookService.updateBook(id, patchedBook);
                return ResponseEntity.ok(savedBook);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private Book applyPatch(JsonMergePatch patch, Book targetBook) throws Exception {
        JsonNode target = objectMapper.convertValue(targetBook, JsonNode.class);
        JsonNode patched = patch.apply(target);
        return objectMapper.treeToValue(patched, Book.class);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchBooks(@RequestParam(required = false) String title,
                                         @RequestParam(required = false) String author) {
        List<Book> books;

        if (title != null) {
            books = bookService.getBooksByTitle(title);
        } else if (author != null) {
            books = bookService.getBooksByAuthor(author);
        } else {
            return ResponseEntity.badRequest().body("Podaj parametr wyszukiwania");
        }

        List<BookInfo> bookInfos = books.stream()
                .map(book -> new BookInfo(book.getTitle(), book.getAuthor(), bookService.hasAvailableCopies(book)))
                .toList();

        return ResponseEntity.ok(bookInfos);
    }

    public BookRepository getBookRepository() {
        return bookRepository;
    }

}
