package com.zerp.bookmanagement.ServiceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zerp.bookmanagement.Model.Book;
import com.zerp.bookmanagement.Repository.BookRepository;
import com.zerp.bookmanagement.Service.BookService;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    public Book saveBook(Book book) {
        book.setCreatedDate(LocalDate.now());
        book.setModifiedDate(LocalDate.now());
        book.setActive(true);
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> findByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    public List<Book> findByBookName(String bookName) {
        return bookRepository.findByBookName(bookName);
    }

    public Optional<Book> findBookById(Long long1) {
        return bookRepository.findById(long1);
    }

}
