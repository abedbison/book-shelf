package com.mitrais.bookshelf.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.mitrais.bookshelf.entity.Book;
import com.mitrais.bookshelf.entity.Shelf;
import com.mitrais.bookshelf.enums.BookStatus;
import com.mitrais.bookshelf.repository.BookRepository;
import com.mitrais.bookshelf.repository.ShelfRepository;
import com.mitrais.bookshelf.service.impl.LibraryServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class LibraryServiceTest {
    
    @InjectMocks
    private LibraryServiceImpl libraryService;

    @Mock
    private BookRepository bookRepository;
    
    @Mock
    private ShelfRepository shelfRepository;
    
    @Test
    public void addBookSuccessTest() {

        when(bookRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(
                new Book(1, "1000", "One Thousand Miles","Jannie Doe", BookStatus.NOT_SHELVED)));
        
        when(shelfRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(
                new Shelf(1, 3, 0, new HashSet<>())));
        
        Book b = bookRepository.findById(1).get();
        Book rtnB = libraryService.addBook(1, b);
        
        verify(bookRepository, Mockito.times(1)).save(Mockito.any(Book.class));
        verify(shelfRepository, Mockito.times(1)).save(Mockito.any(Shelf.class));
        
        assertEquals(BookStatus.SHELVED.name(), rtnB.getStatus().name());
        assertNotNull(rtnB.getMessage());
    }

    @Test
    public void addBookFailedTest() {

        when(bookRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(
                new Book(1, "1000", "One Thousand Miles","Jannie Doe", BookStatus.NOT_SHELVED)));
        
        when(shelfRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(
                new Shelf(2, 3, 3, new HashSet<>())));
        
        Book b = bookRepository.findById(1).get();
        Book rtnB = libraryService.addBook(0, b);
        
        verify(bookRepository, Mockito.never()).save(Mockito.any(Book.class));
        verify(shelfRepository, Mockito.never()).save(Mockito.any(Shelf.class));

        assertEquals(BookStatus.NOT_SHELVED.name(), rtnB.getStatus().name());
        assertNotNull(rtnB.getMessage());
    }

    @Test
    public void removeBookTest() {
        
        Book tBook = new Book(1, "1000", "One Thousand Miles","Jannie Doe", BookStatus.SHELVED);
        Set<Book> tSet = new HashSet<>(Arrays.asList(tBook));

        when(bookRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(
                tBook));
        
        when(shelfRepository.findShelfByBookId(Mockito.anyInt())).thenReturn(Optional.of(
                new Shelf(2, 3, 3, tSet)));

        Book rtnB = libraryService.removeBook(tBook);

        verify(bookRepository, Mockito.times(1)).save(Mockito.any(Book.class));
        verify(shelfRepository, Mockito.times(1)).save(Mockito.any(Shelf.class));

        assertEquals(BookStatus.NOT_SHELVED.name(), rtnB.getStatus().name());
        assertNotNull(rtnB.getMessage());
    }
    
    
    
    
}