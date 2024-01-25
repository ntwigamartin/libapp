package com.mcmillan.libapp.controller;

import org.springframework.web.bind.annotation.RestController;

import com.mcmillan.libapp.exception.ResourceNotFoundException;
import com.mcmillan.libapp.model.Book;
import com.mcmillan.libapp.service.BookServiceI;
import com.mcmillan.libapp.service.BookServiceImpl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class BookController {
    
    private BookServiceI bookService;

    public BookController(BookServiceI bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/books")
    public ResponseEntity<Book> saveBook(@RequestBody Book book) {
        return new ResponseEntity<Book>(bookService.saveBook(book), HttpStatus.CREATED);
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks() {
        return new ResponseEntity<List<Book>>(bookService.getBooks(), HttpStatus.OK);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Object> getBook(@PathVariable("id") String idString) {
        // Validate if the ID is not blank
        if (!StringUtils.hasText(idString)) {
            return new ResponseEntity<>("ID cannot be blank", HttpStatus.BAD_REQUEST);
        }

        // Validate if the ID is a valid Long
        Long id;
        try {
            id = Long.parseLong(idString);
        } catch (NumberFormatException ex) {
            return new ResponseEntity<>("Invalid ID format", HttpStatus.BAD_REQUEST);
        }

        try {
            Book book = bookService.getBookById(id);
            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        
    }
    
    
}
