package com.mitrais.bookshelf.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitrais.bookshelf.entity.Book;
import com.mitrais.bookshelf.enums.BookStatus;
import com.mitrais.bookshelf.repository.BookRepository;
import com.mitrais.bookshelf.service.BookService;

@Service
public class BookServiceImpl implements BookService<Book, Integer> {

    private BookRepository bookRepository;

    public BookServiceImpl() {
        
    }
    
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    
    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Integer id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> findByStatus(BookStatus status) {
        return bookRepository.findByStatus(status);
    }

    @Override
    public List<Book> findByTitleAndStatus(String title, BookStatus status) {
        return bookRepository.findByTitleAndStatus(title, status);
    }
    

}
