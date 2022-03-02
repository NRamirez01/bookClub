package com.nathanielramirez.bookclub.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.nathanielramirez.bookclub.models.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findAll();
    Optional<Book> findById(Long search);
    Optional<Book> findByUserId(Long search);
    String deleteByTitle(String search);
    String deleteById(String search);
}
