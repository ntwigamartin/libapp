package com.mcmillan.libapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mcmillan.libapp.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>{
    
}
