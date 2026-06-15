package com.ankit.lms.repository;

import com.ankit.lms.model.Book;
import com.ankit.lms.model.enums.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByGenre(Genre genre);
    List<Book> findByAvailableCopiesGreaterThan(Integer copies);

    @Query("""
            SELECT b
            FROM Book b
            WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%'))
                OR LOWER(b.author) LIKE LOWER(CONCAT('%', :keyword, '%'))
            """)
    List<Book> searchByTitleOrAuthor(@Param("keyword") String keyword);
}
