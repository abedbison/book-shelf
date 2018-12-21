package com.mitrais.bookshelf.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

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
import com.mitrais.bookshelf.enums.BookStatus;
import com.mitrais.bookshelf.service.impl.BookServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookServiceImpl bookService;
    
    private final String reqMapping = "/book";

    @Test
    public void findValueTest() throws Exception {
    
        when(bookService.findAll()).thenReturn(Arrays.asList(
                new Book(1, "1000", "One Thousand Miles","Jannie Doe", BookStatus.NOT_SHELVED),
                new Book(2, "300", "Three Hundred","Mc. Call", BookStatus.NOT_SHELVED),
                new Book(3, "444", "Story Of Hungry Lion","Shi Si", BookStatus.NOT_SHELVED),
                new Book(4, "8", "Endless Eight: The Infinity","End co.", BookStatus.NOT_SHELVED)));
        
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(reqMapping);
    
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "[{}, {}, {}, {id:4}]"));
    
    }
    
    @Test
    public void failPostMethodTest() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(reqMapping)
                .accept(MediaType.APPLICATION_JSON);
    
        mockMvc.perform(requestBuilder)
                .andExpect(status().isBadRequest());
    }

    @Test
    public void invalidStatusTest() throws Exception {
    
        when(bookService.findByStatus(Mockito.any(BookStatus.class))).thenReturn(Arrays.asList(
                new Book(1, "1000", "One Thousand Miles","Jannie Doe", BookStatus.NOT_SHELVED),
                new Book(2, "300", "Three Hundred","Mc. Call", BookStatus.NOT_SHELVED),
                new Book(3, "444", "Story Of Hungry Lion","Shi Si", BookStatus.NOT_SHELVED),
                new Book(4, "8", "Endless Eight: The Infinity","End co.", BookStatus.NOT_SHELVED)));
        
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(reqMapping + "/status/badstatus");
    
        mockMvc.perform(requestBuilder)
                .andExpect(status().is4xxClientError());
    
    }
    
}
