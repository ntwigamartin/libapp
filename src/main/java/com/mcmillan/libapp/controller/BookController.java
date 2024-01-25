package com.mcmillan.libapp.controller;

import org.springframework.web.bind.annotation.RestController;

import com.mcmillan.libapp.model.Book;
import com.mcmillan.libapp.service.BookServiceI;
import com.mcmillan.libapp.service.BookServiceImpl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


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
    public ResponseEntity<List<Book>> getbooks() {
        return new ResponseEntity<List<Book>>(bookService.getBooks(), HttpStatus.OK);
    }
    
}
