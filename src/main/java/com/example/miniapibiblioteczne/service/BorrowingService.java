package com.example.miniapibiblioteczne.service;

import com.example.miniapibiblioteczne.dto.BorrowingDto;
import com.example.miniapibiblioteczne.dto.BorrowRequestDto;
import com.example.miniapibiblioteczne.exceptions.ResourceNotFoundException;
import com.example.miniapibiblioteczne.encje.BookCopy;
import com.example.miniapibiblioteczne.encje.Borrowing;
import com.example.miniapibiblioteczne.enums.Status;
import com.example.miniapibiblioteczne.encje.User;
import com.example.miniapibiblioteczne.repository.BookCopyRepository;
import com.example.miniapibiblioteczne.repository.BorrowingRepository;
import com.example.miniapibiblioteczne.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowingService {

    private final BorrowingRepository borrowingRepository;
    private final BookCopyRepository bookCopyRepository;
    private final UserRepository userRepository;

    public BorrowingService(BorrowingRepository borrowingRepository,
                            BookCopyRepository bookCopyRepository,
                            UserRepository userRepository) {
        this.borrowingRepository = borrowingRepository;
        this.bookCopyRepository = bookCopyRepository;
        this.userRepository = userRepository;
    }

    public BorrowingDto borrowBook(BorrowRequestDto borrowRequest) {

        if (borrowRequest.getUserId() == null) {
            throw new IllegalArgumentException("User ID must not be null");
        }
        if (borrowRequest.getBookId() == null) {
            throw new IllegalArgumentException("Book ID must not be null");
        }

        // Pobierz użytkownika
        User user = userRepository.findById(borrowRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Znajdź dostępną kopię książki
        BookCopy bookCopy = bookCopyRepository.findFirstByBookIdAndStatus(
                        borrowRequest.getBookId(), Status.AVAILABLE)
                .orElseThrow(() -> new ResourceNotFoundException("No available copy found"));

        // Zmień status kopii na wypożyczoną i zapisz
        bookCopy.setStatus(Status.BORROWED);
        bookCopyRepository.save(bookCopy);

        // Utwórz nowe wypożyczenie
        Borrowing borrowing = new Borrowing();
        borrowing.setUser(user);
        borrowing.setBookCopy(bookCopy);
        borrowing.setBorrowDate(LocalDate.now());
        borrowing.setDueDate(LocalDate.now().plusWeeks(2));
        borrowing.setReturnDate(null);

        // Zapisz wypożyczenie
        borrowingRepository.save(borrowing);


        return BorrowingDto.fromEntity(borrowing);
    }

    public BorrowingDto returnBook(Long borrowingId) {
        Borrowing borrowing = borrowingRepository.findById(borrowingId)
                .orElseThrow(() -> new ResourceNotFoundException("Borrowing not found"));

        if (borrowing.getReturnDate() != null) {
            throw new IllegalStateException("Book already returned");
        }

        borrowing.setReturnDate(LocalDate.now());
        borrowingRepository.save(borrowing);


        BookCopy copy = borrowing.getBookCopy();
        copy.setStatus(Status.AVAILABLE);
        bookCopyRepository.save(copy);

        return BorrowingDto.fromEntity(borrowing);
    }

    public List<BorrowingDto> getUserBorrowingHistory(String username) {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new ResourceNotFoundException("Nie znaleziono użytkownika: " + username));

        List<Borrowing> borrowings = borrowingRepository.findByUser(user);

        return borrowings.stream()
                .map(BorrowingDto::fromEntity)
                .collect(Collectors.toList());
    }


}
