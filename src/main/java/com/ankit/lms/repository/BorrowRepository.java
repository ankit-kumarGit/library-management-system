package com.ankit.lms.repository;

import com.ankit.lms.model.Book;
import com.ankit.lms.model.Borrow;
import com.ankit.lms.model.Member;
import com.ankit.lms.model.enums.BorrowStatus;
import com.ankit.lms.model.enums.MemberStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {

    List<Borrow> findByMember(Member member);
    List<Borrow> findByBook(Book book);
    List<Borrow> findByStatus(BorrowStatus status);

}
