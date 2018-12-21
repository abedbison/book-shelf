package com.mitrais.bookshelf.service;

import java.util.List;
import java.util.Optional;

public interface Service<T, ID> {

    List<T> findAll();
    Optional<T> findById(ID id);
    T save(T t);
    void delete(ID id);
}
