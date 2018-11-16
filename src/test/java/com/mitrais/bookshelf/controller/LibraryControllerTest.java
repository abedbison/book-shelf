package com.mitrais.bookshelf.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.mitrais.bookshelf.entity.Book;
import com.mitrais.bookshelf.entity.Shelf;
import com.mitrais.bookshelf.enums.BookStatus;
import com.mitrais.bookshelf.service.impl.LibraryServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(LibraryController.class)
public class LibraryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LibraryServiceImpl libraryService;
    
    private final String reqMapping = "/library";

    @Test
    public void getShelfTest() throws Exception {

        when(libraryService.findAll()).thenReturn(Arrays.asList(
                new Shelf(1, 3, 0, new HashSet<>()),
                new Shelf(2, 2, 0, new HashSet<>()),
                new Shelf(3, 2, 0, new HashSet<>())));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(reqMapping);
    
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "[{}, {}, {}]"));
    }
    

    @Test
    public void testAddBook() throws Exception {
        
        Book b = new Book(1, "1000", "One Thousand Miles","Jannie Doe", BookStatus.NOT_SHELVED);

        when(libraryService.addBook(Mockito.anyInt(), Mockito.any(Book.class))).thenReturn(b);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(reqMapping + "/addbook")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"bookId\":1, \"shelfId\":1}");
    
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("{status:\"NOT_SHELVED\"}"));
    }
    
}
