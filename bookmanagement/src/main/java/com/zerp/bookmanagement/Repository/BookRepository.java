package com.zerp.bookmanagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zerp.bookmanagement.Model.Book;

import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByAuthor(String author);

    List<Book> findByBookName(String bookName);
    
}
