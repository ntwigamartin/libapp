package com.mcmillan.libapp.service;

import java.util.List;

import com.mcmillan.libapp.model.Book;

public interface BookServiceI {
    Book saveBook(Book book);
    List<Book> getBooks();
    Book getBookById(Long id);
}
