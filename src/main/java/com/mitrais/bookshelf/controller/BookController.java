package com.mitrais.bookshelf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mitrais.bookshelf.entity.Book;
import com.mitrais.bookshelf.enums.BookStatus;
import com.mitrais.bookshelf.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {

    private BookService<Book, Integer> bookService;

    @Autowired
    public void setBookService(BookService<Book, Integer> bookService) {
        this.bookService = bookService;
    }
    
    @GetMapping
    public List<Book> findAll() {
        return bookService.findAll();
    }
    
    @GetMapping("/status/{status}")
    public List<Book> findByStatus(
            @PathVariable(name="status") BookStatus status) {
        return (status != null)?
                bookService.findByStatus(status):
                    bookService.findAll();
    }
    
    @GetMapping("/search")
    public List<Book> searchBook(
            @RequestParam(name="title",defaultValue="") String title,
            @RequestParam(name="status",defaultValue="NOT_SHELVED") BookStatus status) {
        return (title.isEmpty())?
                bookService.findByStatus(status):
                bookService.findByTitleAndStatus(title, status);
    }
    
    @GetMapping("/value/{id}")
    public Book findAll(@PathVariable String id) {
        return bookService.findById((Integer.parseInt(id))).orElse(new Book());
    }
    
}
