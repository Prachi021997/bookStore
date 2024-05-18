package com.bookStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookStore.entity.Book;
//References to generic type--DataType and DataType of ID as parameter
@Repository
public interface BookRepository extends JpaRepository<Book,Integer>{

}
