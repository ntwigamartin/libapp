package com.mcmillan.libapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mcmillan.libapp.exception.ResourceNotFoundException;
import com.mcmillan.libapp.model.Book;
import com.mcmillan.libapp.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookServiceI {
    
    
    private final BookRepository bookRepository;


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

    @Override
    public String deleteBook(Long id) {
        Book book = getBookById(id);
        if (book != null) {
            bookRepository.delete(book);
            return "Book with id " + id + " deleted successfully";
        }
        
        return null;
    }

    
}
