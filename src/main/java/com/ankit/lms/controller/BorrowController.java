package com.ankit.lms.controller;

import com.ankit.lms.model.Borrow;
import com.ankit.lms.model.enums.BorrowStatus;
import com.ankit.lms.service.BorrowService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrows")
public class BorrowController {

    // DI
    private final BorrowService service;

    // Constructor Injection
    public BorrowController(BorrowService service) {
        this.service = service;
    }

    // Borrow a Book
    @PostMapping("/borrow")
    public Borrow borrowBook(
            @RequestParam Long memberId,
            @RequestParam Long bookId) {

        return service.borrowBook(memberId, bookId);
    }

    // Return a Book
    @PatchMapping("/return/{borrowId}")
    public Borrow returnBook(@PathVariable Long borrowId) {

        return service.returnBook(borrowId);
    }

    // Get All Borrow Records
    @GetMapping
    public List<Borrow> getAllBorrowRecords() {

        return service.getAllBorrowRecords();
    }

    // Get Borrow Record By Id
    @GetMapping("/{id}")
    public Borrow getBorrowRecordById(@PathVariable Long id) {

        return service.getBorrowRecordById(id);
    }

    // Get Borrows By Member
    @GetMapping("/member/{memberId}")
    public List<Borrow> getBorrowsByMember(
            @PathVariable Long memberId) {

        return service.getBorrowsByMember(memberId);
    }

    // Get Borrows By Status
    @GetMapping("/status/{status}")
    public List<Borrow> getBorrowsByStatus(
            @PathVariable BorrowStatus status) {

        return service.getBorrowsByStatus(status);
    }
}