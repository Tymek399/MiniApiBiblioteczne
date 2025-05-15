package com.example.miniapibiblioteczne;

import com.example.miniapibiblioteczne.model.Book;
import com.example.miniapibiblioteczne.model.BookCopy;
import com.example.miniapibiblioteczne.model.Status;
import com.example.miniapibiblioteczne.repository.BookCopyRepository;
import com.example.miniapibiblioteczne.repository.BookRepository;
import com.example.miniapibiblioteczne.repository.BorrowingRepository;
import com.example.miniapibiblioteczne.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

    private BookRepository bookRepository;
    private BookCopyRepository bookCopyRepository;
    private BorrowingRepository borrowingRepository;
    private BookService bookService;

    @BeforeEach
    void setUp() {
        bookRepository = mock(BookRepository.class);
        bookCopyRepository = mock(BookCopyRepository.class);
        borrowingRepository = mock(BorrowingRepository.class);
        bookService = new BookService(bookRepository, bookCopyRepository, borrowingRepository);
    }

    @Test
    void shouldReturnCountOfAvailableCopies() {
        Book book = new Book();
        book.setId(1L);

        BookCopy availableCopy = new BookCopy();
        availableCopy.setId(10L);
        availableCopy.setStatus(Status.AVAILABLE);

        BookCopy borrowedCopy = new BookCopy();
        borrowedCopy.setId(11L);
        borrowedCopy.setStatus(Status.AVAILABLE);

        List<BookCopy> copies = Arrays.asList(availableCopy, borrowedCopy);

        when(bookCopyRepository.findByBook(book)).thenReturn(copies);
        when(borrowingRepository.existsByBookCopyIdAndReturnDateIsNull(10L)).thenReturn(false);
        when(borrowingRepository.existsByBookCopyIdAndReturnDateIsNull(11L)).thenReturn(true); // this one is borrowed

        long availableCount = bookService.countAvailableCopies(book);

        assertEquals(1, availableCount);
    }
}
