package com.example.miniapibiblioteczne.controlers;

import com.example.miniapibiblioteczne.dto.BorrowRequestDto;
import com.example.miniapibiblioteczne.dto.BorrowingDto;
import com.example.miniapibiblioteczne.service.BorrowingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrowings")
public class BorrowingControler {

    private final BorrowingService borrowingService;

    public BorrowingControler(BorrowingService borrowingService) {
        this.borrowingService = borrowingService;
    }

    @PostMapping("/borrow")
    public ResponseEntity<BorrowingDto> borrowBook(@RequestBody BorrowRequestDto request) {
        return ResponseEntity.ok(borrowingService.borrowBook(request));
    }

    @PostMapping("/return/{borrowingId}")
    public ResponseEntity<BorrowingDto> returnBook(@PathVariable Long borrowingId) {
        return ResponseEntity.ok(borrowingService.returnBook(borrowingId));
    }

    @GetMapping("/history")
    public ResponseEntity<List<BorrowingDto>> getUserBorrowingHistory(@RequestParam String username) {
        return ResponseEntity.ok(borrowingService.getUserBorrowingHistory(username));
    }
}
