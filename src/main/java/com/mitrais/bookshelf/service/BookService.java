package com.mitrais.bookshelf.service;

import java.util.List;

import com.mitrais.bookshelf.enums.BookStatus;

public interface BookService<T, ID> extends Service<T, ID>{

    List<T> findByStatus(BookStatus status);
    List<T> findByTitleAndStatus(String title, BookStatus status);
}
