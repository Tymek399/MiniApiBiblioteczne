package com.example.miniapibiblioteczne.controlers;

import com.example.miniapibiblioteczne.dto.BorrowRequestDto;
import com.example.miniapibiblioteczne.dto.BorrowingDto;
import com.example.miniapibiblioteczne.service.BorrowingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/borrowings")
@RequiredArgsConstructor
public class BorrowingController {

    private final BorrowingService borrowingService;

    @PostMapping("/borrow")
    public ResponseEntity<BorrowingDto> borrowBook(@Valid @RequestBody BorrowRequestDto req) {
        BorrowingDto result = borrowingService.borrowBook(req);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/return/{barcode}")
    public ResponseEntity<BorrowingDto> returnBook(@PathVariable String barcode) {
        BorrowingDto result = borrowingService.returnBook(barcode);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/history/{userName}")
    public ResponseEntity<List<BorrowingDto>> history(@PathVariable String userName) {
        List<BorrowingDto> history = borrowingService.getUserBorrowingHistory(userName);
        return ResponseEntity.ok(history);
    }
}
