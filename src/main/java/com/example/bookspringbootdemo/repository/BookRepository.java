package com.example.bookspringbootdemo.repository;

import com.example.bookspringbootdemo.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {

//    public List<Book> findAll();

    public List<Book> findByName(String name);

    public List<Book> findByNumber(int number);

//    Book findOne(Integer id);
}
