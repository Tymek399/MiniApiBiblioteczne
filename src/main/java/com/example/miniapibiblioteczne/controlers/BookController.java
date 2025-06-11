package com.example.miniapibiblioteczne.controlers;

import com.example.miniapibiblioteczne.dto.BookDto;
import com.example.miniapibiblioteczne.service.BookService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @PostMapping
    public BookDto addBook(@RequestBody @Valid BookDto bookDto) {
        return bookService.addBook(bookDto);
    }

    @GetMapping
    public List<BookDto> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{barcode}")
    public ResponseEntity<BookDto> getBookByBarcode(@PathVariable String barcode) {
        return bookService.getBookByBarcode(barcode)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{isbn}")
    public ResponseEntity<BookDto> patchBook(@PathVariable String isbn,  @Valid @RequestBody BookDto bookDto) {
        return bookService.partialUpdateBook(isbn, bookDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{barcode}")
    public ResponseEntity<Void> deleteBook(@PathVariable String barcode) {
        return bookService.deleteBookByBarcode(barcode);
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookDto>> searchBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author) {

        List<BookDto> books = bookService.searchBooks(title, author);

        if (books.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(books);
        }
    }

}
