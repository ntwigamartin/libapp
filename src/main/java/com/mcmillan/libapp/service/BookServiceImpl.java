package com.mcmillan.libapp.service;

import org.springframework.stereotype.Service;

import com.mcmillan.libapp.repository.BookRepository;

@Service
public class BookServiceImpl {
    
    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    
}
