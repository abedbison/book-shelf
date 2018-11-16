package com.mitrais.bookshelf.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.mitrais.bookshelf.enums.BookStatus;

@Entity
@JsonInclude(Include.NON_NULL)
public class Book {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(length=32)
    private String isbn;
    
    @Column(length=255)
    private String title;

    @Column(length=128)
    private String author;
    
    @Enumerated(EnumType.STRING)
    @Column(length=16)
    private BookStatus status;
    
    @Transient
    private String message;

    public Book() {
        
    }

    public Book(String isbn, String author, BookStatus status) {
        this.isbn = isbn;
        this.author = author;
        this.status = status;
    }
    
    public Book(Integer id, String isbn, String title, String author, BookStatus status) {
        super();
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Book [id=" + id + ", isbn=" + isbn + ", title=" + title + ", author=" + author + ", status=" + status
                + ", message=" + message + "]";
    }
    
    
    
}
