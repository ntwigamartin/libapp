package com.mcmillan.libapp.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mcmillan.libapp.model.Book;

public interface BookServiceI {
    Book saveBook(Book book);
    List<Book> getBooks();
    Book getBookById(Long id);
    Book updateBookById(Long id, Book obj);
    ResponseEntity<Object> deleteBook(Long id);
}
