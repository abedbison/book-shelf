package com.mitrais.bookshelf.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitrais.bookshelf.entity.Book;
import com.mitrais.bookshelf.entity.Shelf;
import com.mitrais.bookshelf.enums.BookStatus;
import com.mitrais.bookshelf.repository.BookRepository;
import com.mitrais.bookshelf.repository.ShelfRepository;
import com.mitrais.bookshelf.service.LibraryService;

@Service
public class LibraryServiceImpl implements LibraryService<Shelf, Integer> {
    
    private ShelfRepository shelfRepository;
    private BookRepository bookRepository;
    
    public LibraryServiceImpl() {
        
    }
    
    public LibraryServiceImpl(ShelfRepository shelfRepository, BookRepository bookRepository) {
        this.shelfRepository = shelfRepository;
        this.bookRepository = bookRepository;
    }
    
    @Autowired
    public void setShelfRepository(ShelfRepository shelfRepository) {
        this.shelfRepository = shelfRepository;
    }
    
    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Shelf> findAll() {
        return shelfRepository.findAll();
    }

    @Override
    public Optional<Shelf> findById(Integer id) {
        return shelfRepository.findById(id);
    }

    @Override
    public Book addBook(Integer id, Book b) {
        Optional<Shelf> oShelf = shelfRepository.findById(id);
        Optional<Book> oBook = bookRepository.findById(b.getId());
        oBook.ifPresent(book -> {
            oShelf.ifPresent(shelf -> {
                if (shelf.getCurrentCapacity() < shelf.getMaxCapacity() && 
                        book.getStatus().equals(BookStatus.NOT_SHELVED)) {
                    int newCapacity = shelf.getCurrentCapacity() + 1;
                    shelf.setCurrentCapacity(newCapacity);
                    Set<Book> books = shelf.getBook();
                    books.add(book);
                    shelfRepository.save(shelf);
                    
                    book.setStatus(BookStatus.SHELVED);
                    bookRepository.save(book);
                    book.setMessage("Book Shelved");
                } else {
                    book.setMessage("Add failed, not enought capacity");
                }
            });
        });
        
        b.setMessage("Add failed, unable to find Book/Shelf");
        return oBook.orElse(b);
    }
    
    @Override
    public Book removeBook(Book b) {
        Optional<Shelf> oShelf = shelfRepository.findShelfByBookId(b.getId());
        Optional<Book> oBook = bookRepository.findById(b.getId());
        oBook.ifPresent(book -> {
            book.setMessage("Remove failed, unable to find shelf");
            oShelf.ifPresent(shelf -> {
                Set<Book> books = shelf.getBook();
                if(books.remove(book) &&
                        book.getStatus().equals(BookStatus.SHELVED)) {
                    int newCapacity = shelf.getCurrentCapacity() - 1;
                    shelf.setCurrentCapacity(newCapacity);
                    shelfRepository.save(shelf);

                    book.setStatus(BookStatus.NOT_SHELVED);
                    bookRepository.save(book);
                    book.setMessage("Book unshelved");
                } else {
                    book.setMessage("Remove failed, book is already not shelved");
                }
            });
        });
        
        b.setMessage("Remove failed, unable to find book");
        return oBook.orElse(b);
    }

    @Override
    public Shelf save(Shelf t) {
        return shelfRepository.save(t);
    }
    
    @Override
    public void delete(Integer id) {
        shelfRepository.findById(id).ifPresent(shelf -> {
            shelfRepository.delete(shelf);
        });
    }

}
