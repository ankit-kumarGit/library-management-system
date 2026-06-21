package com.ankit.lms.service;

import com.ankit.lms.exception.ResourceNotFoundException;
import com.ankit.lms.model.Book;
import com.ankit.lms.model.Borrow;
import com.ankit.lms.model.Member;
import com.ankit.lms.model.enums.BorrowStatus;
import com.ankit.lms.model.enums.MemberStatus;
import com.ankit.lms.repository.BookRepository;
import com.ankit.lms.repository.BorrowRepository;
import com.ankit.lms.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BorrowService {

    //DI
    private final BorrowRepository borrowRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    //COnstructor INjection

    public BorrowService(BorrowRepository borrowRepository, BookRepository bookRepository, MemberRepository memberRepository) {
        this.borrowRepository = borrowRepository;
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
    }


    //public Borrow borrowBook(Long memberId, Long bookId)
    public Borrow borrowBook(Long memberId, Long bookId)
    {
        //fetch member
        Member member= memberRepository.findById(memberId).orElseThrow(()->
                new ResourceNotFoundException("Member not found with id: " + memberId));
        //fetch book
        Book book= bookRepository.findById(bookId).orElseThrow(()->
                new ResourceNotFoundException("Book not found with id: " + bookId));

        // check book availability
        if(book.getAvailableCopies()<=0)
        {
            throw new RuntimeException("Book is not available");
        }

        if(member.getStatus() != MemberStatus.ACTIVE)
        {
            throw new RuntimeException("Member is suspended");
        }

        Borrow borrow= Borrow.builder()
                .book(book)
                .member(member)
                .status(BorrowStatus.BORROWED)
                .dueDate(LocalDateTime.now().plusDays(14))
                .build();

        book.setAvailableCopies(book.getAvailableCopies()-1);
        bookRepository.save(book);

        return borrowRepository.save(borrow);
    }

    //public Borrow returnBook(Long borrowId)
    public Borrow returnBook(Long borrowId)
    {
        //Find Borrow Record
        Borrow borrow= borrowRepository.findById(borrowId).orElseThrow(()->
                new ResourceNotFoundException("Borrow record not found with id: " +borrowId));

        if(borrow.getStatus() == BorrowStatus.RETURNED)
        {
            throw new RuntimeException("Book already returned");
        }

        Book book = borrow.getBook();

        borrow.setStatus(BorrowStatus.RETURNED);
        borrow.setReturnDate(LocalDateTime.now());

        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);

        return borrowRepository.save(borrow);
    }

    public List<Borrow> getAllBorrowRecords()
    {
        return borrowRepository.findAll();
    }

    public Borrow getBorrowRecordById(Long id)
    {
        return borrowRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Borrow record not found with id: " + id));
    }

    public List<Borrow> getBorrowsByStatus(BorrowStatus status)
    {
        return borrowRepository.findByStatus(status);
    }

    public List<Borrow> getBorrowsByMember(Long memberId)
    {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Member not found with id: " + memberId));

        return borrowRepository.findByMember(member);
    }


}
