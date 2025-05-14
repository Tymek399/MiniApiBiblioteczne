package com.example.miniapibiblioteczne.repository;

import com.example.miniapibiblioteczne.model.BorrowBook;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface BorrowingRepository extends JpaRepository<BorrowBook, Long> {
    List<BorrowBook> findAllByUserId(Long userId);
    List<BorrowBook> findByBookCopyIdAndReturnDateIsNull(Long bookCopyId);
}
