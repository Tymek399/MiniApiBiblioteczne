package com.example.miniapibiblioteczne.service;

import com.example.miniapibiblioteczne.dto.BorrowRequestDto;
import com.example.miniapibiblioteczne.dto.BorrowingDto;
import com.example.miniapibiblioteczne.encje.Book;
import com.example.miniapibiblioteczne.encje.Borrowing;
import com.example.miniapibiblioteczne.encje.User;
import com.example.miniapibiblioteczne.exceptions.ResourceNotFoundException;
import com.example.miniapibiblioteczne.repository.BookRepository;
import com.example.miniapibiblioteczne.repository.BorrowingRepository;
import com.example.miniapibiblioteczne.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowingService {

    private final BorrowingRepository borrowingRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public BorrowingService(BorrowingRepository borrowingRepository,
                            BookRepository bookRepository,
                            UserRepository userRepository) {
        this.borrowingRepository = borrowingRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public BorrowingDto borrowBook(BorrowRequestDto req) {
        User user = userRepository.findByUserName(req.getUserName().trim())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Book book = bookRepository.findByBarcode(req.getBarcode().trim())
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        boolean alreadyBorrowed = borrowingRepository.existsByBookAndReturnDateIsNull(book);
        if (alreadyBorrowed) {
            throw new IllegalStateException("Book is currently borrowed");
        }

        Borrowing b = new Borrowing();
        b.setUser(user);
        b.setBook(book);
        b.setBorrowDate(LocalDate.now());
        b.setDueDate(LocalDate.now().plusWeeks(4));
        b.setReturnDate(null);

        borrowingRepository.save(b);
        return BorrowingDto.fromEntity(b);
    }

    public BorrowingDto returnBook(String barcode) {
        Borrowing borrowing = borrowingRepository.findByBook_Barcode(barcode.trim())
                .orElseThrow(() -> new ResourceNotFoundException("Borrowing not found"));

        if (borrowing.getReturnDate() != null) {
            throw new IllegalStateException("Already returned");
        }

        borrowing.setReturnDate(LocalDate.now());
        borrowingRepository.save(borrowing);

        return BorrowingDto.fromEntity(borrowing);
    }

    public List<BorrowingDto> getUserBorrowingHistory(String userName) {
        User user = userRepository.findByUserName(userName.trim())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return borrowingRepository.findByUser(user).stream()
                .map(BorrowingDto::fromEntity)
                .collect(Collectors.toList());
    }
}
