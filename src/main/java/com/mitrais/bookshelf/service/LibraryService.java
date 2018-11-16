package com.mitrais.bookshelf.service;

import com.mitrais.bookshelf.entity.Book;

public interface LibraryService<T, ID> extends Service<T, ID> {

    Book addBook(ID id, Book b);
    Book removeBook(Book b);
    
}
