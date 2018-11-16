package com.mitrais.bookshelf.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class LibraryRequest {

    private Integer bookId;
    private Integer shelfId;
    
    public Integer getBookId() {
        return bookId;
    }
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
    public Integer getShelfId() {
        return shelfId;
    }
    public void setShelfId(Integer shelfId) {
        this.shelfId = shelfId;
    }
}
