package com.example.miniapibiblioteczne.service;

import com.example.miniapibiblioteczne.model.Book;
import com.example.miniapibiblioteczne.model.BookCopy;
import com.example.miniapibiblioteczne.model.Status;
import com.example.miniapibiblioteczne.repository.BookCopyRepository;
import com.example.miniapibiblioteczne.repository.BookRepository;
import com.example.miniapibiblioteczne.repository.BorrowingRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;
@Service
public class BookService {
    private final BookCopyRepository bookCopyRepository;
    private final BorrowingRepository borrowingRepository;
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository, BookCopyRepository bookCopyRepository, BorrowingRepository borrowingRepository) {
        this.bookRepository = bookRepository;
        this.bookCopyRepository = bookCopyRepository;
        this.borrowingRepository = borrowingRepository;
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Book updateBook(Long id, Book updatedBook) {
        return bookRepository.findById(id)
                .map(book -> {
                    book.setTitle(updatedBook.getTitle());
                    book.setAuthor(updatedBook.getAuthor());
                    book.setIsbn(updatedBook.getIsbn());
                    book.setPublicationYear(updatedBook.getPublicationYear());
                    return bookRepository.save(book);
                })
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public List<Book> getBooksByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    public List<Book> getBooksByTitle(String title) {
        return bookRepository.findByTitle(title);
    }


    public boolean getAvailableCopies(Long bookCopyId) {
        BookCopy copy = bookCopyRepository.findById(bookCopyId)
                .orElseThrow(() -> new EntityNotFoundException("Book copy not found"));

        if (copy.getStatus() != Status.AVAILABLE) {
            return false;
        }

        boolean isCurrentlyBorrowed = borrowingRepository.existsByBookCopyIdAndReturnDateIsNull(bookCopyId);
        return !isCurrentlyBorrowed;
    }
    public boolean hasAvailableCopies(Book book) {
        List<BookCopy> copies = bookCopyRepository.findByBook(book);
        for (BookCopy copy : copies) {
            if (copy.getStatus() == Status.AVAILABLE &&
                    !borrowingRepository.existsByBookCopyIdAndReturnDateIsNull(copy.getId())) {
                return true;
            }
        }
        return false;
    }

    public long countAvailableCopies(Book book) {
        List<BookCopy> copies = bookCopyRepository.findByBook(book);

        return copies.stream()
                .filter(copy -> copy.getStatus() == Status.AVAILABLE)
                .filter(copy -> !borrowingRepository.existsByBookCopyIdAndReturnDateIsNull(copy.getId()))
                .count();
    }

}

