package com.mcmillan.libapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mcmillan.libapp.exception.ResourceNotFoundException;
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

    @Override
    public Book getBookById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }

        return bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book", id));
        
    }

    @Override
    public Book updateBookById(Long id, Book obj) {

        Book existingBook = getBookById(id);

        if (obj.getPrice() != null) {
            existingBook.setPrice(obj.getPrice());
        }
        
        if (obj.getISBN() != null) {
            existingBook.setISBN(obj.getISBN());
        }

        if (obj.getTitle() != null) {
            existingBook.setTitle(obj.getTitle());
        }
        
        
        return bookRepository.save(existingBook);

    }
}
