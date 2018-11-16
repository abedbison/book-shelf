package com.mitrais.bookshelf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mitrais.bookshelf.entity.Book;
import com.mitrais.bookshelf.enums.BookStatus;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findByStatus(BookStatus status);
    List<Book> findByTitleAndStatus(String title, BookStatus status);
}
