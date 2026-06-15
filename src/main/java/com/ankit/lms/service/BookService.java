package com.ankit.lms.service;

import com.ankit.lms.exception.ResourceNotFoundException;
import com.ankit.lms.model.Book;
import com.ankit.lms.model.enums.Genre;
import com.ankit.lms.repository.BookRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class BookService {

    //DI
    private final BookRepository repository;

    // construction injection
    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    // add book
    public Book saveBook(Book book)
    {
        return repository.save(book);
    }

    //get all books
    public List<Book> getAllBooks()
    {
        return repository.findAll();
    }

    //get book by ID
    public Book getBookById(Long id)
    {
        return repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Book not found with id: " + id));
    }

    //update book
    public Book updateBook(Long id, Book updatedbook)
    {
        //first find
//        Book book= repository.findById(id).orElseThrow(()->
//                new ResourceNotFoundException("Book not found with id: "+id));
        Book book= getBookById(id);

            book.setAuthor(updatedbook.getAuthor());
            book.setIsbn(updatedbook.getIsbn());
            book.setGenre(updatedbook.getGenre());
            book.setTitle(updatedbook.getTitle());
            book.setTotalCopies(updatedbook.getTotalCopies());
            book.setAvailableCopies(updatedbook.getAvailableCopies());
        return repository.save(book);
    }

    // delete book
    public void deleteBook(Long id)
    {
        Book book= getBookById(id);
        repository.delete(book);
    }

    //searchBooks
    public List<Book> searchBooks(String keyword)
    {
        return repository.searchByTitleOrAuthor(keyword);
    }

    // get books by genre
    public List<Book> getBooksByGenre(Genre genre)
    {
        return repository.findByGenre(genre);
    }
}
