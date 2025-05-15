package com.example.miniapibiblioteczne.repository;

import com.example.miniapibiblioteczne.model.Borrowing;
import com.example.miniapibiblioteczne.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {
    List<Borrowing> findByUserId(Long userId);
    List<Borrowing> findByUser(User user);
}
