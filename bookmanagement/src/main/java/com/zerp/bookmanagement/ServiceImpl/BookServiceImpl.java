package com.zerp.bookmanagement.ServiceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.zerp.bookmanagement.Model.Book;
import com.zerp.bookmanagement.Repository.BookRepository;
import com.zerp.bookmanagement.Service.BookService;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    public Book saveBook(Book book) {
        try {
            book.setActive(true);
            book.setCreatedDate(LocalDateTime.now());
            book.setModifiedDate(LocalDateTime.now());
            return bookRepository.save(book);
        } catch (Exception e) {
            throw new DataIntegrityViolationException("Data is repeated");
        }
    }

    public List<Book> getAllBooks() {
        try {
            return bookRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error while retrieving books", e);
        }
    }

    public List<Book> findByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    public List<Book> findByBookName(String bookName) {
        return bookRepository.findByBookName(bookName);
    }

    public Optional<Book> findBookById(Long long1) {
        if (long1 != null)
            return bookRepository.findById(long1);
        else
            return null;
    }

}
