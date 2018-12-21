package com.mitrais.bookshelf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mitrais.bookshelf.entity.Book;
import com.mitrais.bookshelf.entity.Shelf;
import com.mitrais.bookshelf.service.LibraryService;

@RestController
@RequestMapping("/library")
@CrossOrigin(origins="*")
public class LibraryController {

    private LibraryService<Shelf, Integer> libraryService;
    
    @Autowired
    public void setLibraryService(LibraryService<Shelf, Integer> libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping
    public List<Shelf> findAll() {
        return libraryService.findAll();
    }

    @GetMapping("/value/{id}")
    public Shelf findById(@PathVariable Integer id) {
        return libraryService.findById(id).orElse(new Shelf());
    }
    
    @PostMapping("/addbook")
    public Book addBookPost(@RequestBody LibraryRequest req) {
        Book book = new Book();
        book.setId(req.getBookId());
        
        return libraryService.addBook(req.getShelfId(), book);
    }
    
    @PostMapping("/removebook")
    public Book removeBookPost(@RequestBody LibraryRequest req) {
        Book book = new Book();
        book.setId(req.getBookId());

        return libraryService.removeBook(book);
    }
    
    @PostMapping
    public Shelf save(@RequestBody Shelf s) {
        return libraryService.save(s);
    }
    
    @DeleteMapping
    @PostMapping("/delete")
    public Shelf delete(@RequestBody Shelf s) {
        libraryService.delete(s.getId());
        return s;
    }
    
}
