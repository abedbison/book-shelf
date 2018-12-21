package com.mitrais.bookshelf.entity;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
public class Shelf {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name="max_capacity")
    private int maxCapacity;

    @Column(name="current_capacity")
    private int currentCapacity;
    
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "shelf_book", joinColumns = @JoinColumn(name="shelf_id"))
    private Set<Book> book;
    
    public Shelf() {
        
    }

    public Shelf(Integer id, int maxCapacity, int currentCapacity, Set<Book> book) {
        super();
        this.id = id;
        this.maxCapacity = maxCapacity;
        this.currentCapacity = currentCapacity;
        this.book = book;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(int currentCapacity) {
        this.currentCapacity = currentCapacity;
    }
    

    public Set<Book> getBook() {
        return book;
    }

    public void setBook(Set<Book> book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Shelf [id=" + id + ", maxCapacity=" + maxCapacity + ", currentCapacity=" + currentCapacity + ", book="
                + book + "]";
    }
    
    
    
}
