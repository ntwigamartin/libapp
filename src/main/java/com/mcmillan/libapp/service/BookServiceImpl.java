package com.mcmillan.libapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mcmillan.libapp.model.Book;
import com.mcmillan.libapp.repository.BookRepository;

@Service
public class BookServiceImpl implements BookServiceI {
    
    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book saveBook(Book book) {
        System.out.println(book);
        if (book != null) {
            bookRepository.save(book);
        }
        return book;
    }

    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    
}
