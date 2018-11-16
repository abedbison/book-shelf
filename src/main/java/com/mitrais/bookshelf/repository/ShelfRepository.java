package com.mitrais.bookshelf.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mitrais.bookshelf.entity.Shelf;

@Repository
public interface ShelfRepository extends JpaRepository<Shelf, Integer>{

    @Query("SELECT s FROM Shelf s, IN(s.book) b WHERE b.id = ?1")
    public Optional<Shelf> findShelfByBookId(Integer bookId);
}
