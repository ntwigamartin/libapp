package com.mcmillan.libapp.controller;

import org.springframework.web.bind.annotation.RestController;

import com.mcmillan.libapp.Utils.ValidateId;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/books")
public class BookController {
    
    private BookServiceI bookService;

    public BookController(BookServiceI bookService) {
        this.bookService = bookService;
    }

    @PostMapping()
    public ResponseEntity<Book> saveBook(@RequestBody Book book) {
        return new ResponseEntity<Book>(bookService.saveBook(book), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Book>> getBooks() {
        return new ResponseEntity<List<Book>>(bookService.getBooks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getBook(@PathVariable("id") String idString) {

       if (ValidateId.validate(idString) != null) {
            return ValidateId.validate(idString);
       }

       Long id = Long.parseLong(idString);
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

    @PutMapping("{id}")
    public ResponseEntity<Object> updateBookById(@PathVariable("id") String idString, @RequestBody Book obj) {
        
        if (ValidateId.validate(idString) != null) {
            return ValidateId.validate(idString);
       }

       try {
        if (obj != null) {
            Long id = Long.parseLong(idString);
            Book book = bookService.updateBookById(id, obj);
            return new ResponseEntity<>(book, HttpStatus.OK);
           } else {
            return new ResponseEntity<>("Invalid data", HttpStatus.BAD_REQUEST);
           }
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable("id") String idString) {
        if (ValidateId.validate(idString) != null) {
            return ValidateId.validate(idString);
       }

       try {
            Long id = Long.parseLong(idString);
            return new ResponseEntity<>(bookService.deleteBook(id), HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
}
