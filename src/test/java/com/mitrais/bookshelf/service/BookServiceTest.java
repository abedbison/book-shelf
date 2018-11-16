package com.mitrais.bookshelf.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.mitrais.bookshelf.entity.Book;
import com.mitrais.bookshelf.enums.BookStatus;
import com.mitrais.bookshelf.repository.BookRepository;
import com.mitrais.bookshelf.service.impl.BookServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {
    
    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookRepository bookRepository;
    
    @Test
    public void findAllTest() {
        when(bookRepository.findAll()).thenReturn(Arrays.asList(
                new Book(1, "1000", "One Thousand Miles","Jannie Doe", BookStatus.NOT_SHELVED),
                new Book(2, "300", "Three Hundred","Mc. Call", BookStatus.NOT_SHELVED),
                new Book(3, "444", "Story Of Hungry Lion","Shi Si", BookStatus.NOT_SHELVED),
                new Book(4, "8", "Endless Eight: The Infinity","End co.", BookStatus.NOT_SHELVED)));

        assertEquals(4, bookService.findAll().size());
    }

    @Test
    public void findSingleValueTest() {
        when(bookRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(
                new Book(1, "1000", "One Thousand Miles","Jannie Doe", BookStatus.NOT_SHELVED)));
        
        Optional<Book> oBook = bookService.findById(3);
        assertTrue(oBook.isPresent());
    }
    
    @Test
    public void findByStatusTest() {
        bookService.findByTitleAndStatus("", BookStatus.SHELVED);
        
        verify(bookRepository, atLeastOnce())
                .findByTitleAndStatus(Mockito.anyString(), Mockito.any(BookStatus.class));
    }
    
    
    
}
