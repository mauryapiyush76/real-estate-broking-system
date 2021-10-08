package com.casestudy.rebsa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casestudy.rebsa.model.Book;


@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

}
