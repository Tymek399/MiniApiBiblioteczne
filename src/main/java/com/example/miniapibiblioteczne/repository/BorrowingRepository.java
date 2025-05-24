package com.example.miniapibiblioteczne.repository;

import com.example.miniapibiblioteczne.encje.Book;
import com.example.miniapibiblioteczne.encje.Borrowing;
import com.example.miniapibiblioteczne.encje.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {
    List<Borrowing> findByUser(User user);
    boolean existsByBookAndReturnDateIsNull(Book book);
    Optional<Borrowing> findByBook_Barcode(String barcode);
}
