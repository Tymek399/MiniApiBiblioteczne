package com.example.miniapibiblioteczne.controlers;

import com.example.miniapibiblioteczne.dto.BorrowRequestDto;
import com.example.miniapibiblioteczne.dto.BorrowingDto;
import com.example.miniapibiblioteczne.service.BorrowingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/borrowings")
@RequiredArgsConstructor
public class BorrowingController {

    private final BorrowingService borrowingService;

    @PostMapping("/borrow")
    public BorrowingDto borrowBook(@Valid @RequestBody BorrowRequestDto req) {
        return borrowingService.borrowBook(req);
    }

    @PostMapping("/return/{barcode}")
    public BorrowingDto returnBook(@PathVariable String barcode) {
        return borrowingService.returnBook(barcode);
    }

    @GetMapping("/history/{userName}")
    public List<BorrowingDto> history(@PathVariable String userName) {
        return borrowingService.getUserBorrowingHistory(userName);
    }
}
