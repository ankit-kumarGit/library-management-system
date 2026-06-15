package com.ankit.lms.controller;

import com.ankit.lms.model.Book;
import com.ankit.lms.model.enums.Genre;
import com.ankit.lms.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    //DI service
    private final BookService service;

    // Constructor Injection
    public BookController(BookService service) {
        this.service = service;
    }


    //post
    @PostMapping
    public ResponseEntity<Book> saveBook(@Valid @RequestBody Book book)
    {
        service.saveBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveBook(book));
    }

    //get all books
    @GetMapping
    public List<Book> getAllBooks()
    {
        return service.getAllBooks();
    }

    //get book by ID
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id)
    {
        return service.getBookById(id);
    }

    //update book
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id,@Valid @RequestBody Book updatedbook)
    {
        return service.updateBook(id, updatedbook);
    }

    // delete book
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id)
    {
        service.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    //searchBooks
    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam String keyword)
    {
        return service.searchBooks(keyword);
    }

    // get books by genre
    @GetMapping("/genre/{genre}")
    public List<Book> getBooksByGenre(@PathVariable Genre genre)
    {
        return service.getBooksByGenre(genre);
    }

}
