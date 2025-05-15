package com.example.miniapibiblioteczne.repository;

import com.example.miniapibiblioteczne.encje.Borrowing;
import com.example.miniapibiblioteczne.encje.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {
    List<Borrowing> findByUser(User user);

    boolean existsByBookCopyIdAndReturnDateIsNull(Long bookCopyId);

}
