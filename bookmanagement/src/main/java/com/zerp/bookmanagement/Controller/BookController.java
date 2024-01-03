package com.zerp.bookmanagement.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zerp.bookmanagement.Model.Book;
import com.zerp.bookmanagement.ServiceImpl.BookServiceImpl;


@RestController
@RequestMapping("/books")
public class BookController {
 private final BookServiceImpl bookService;

    public BookController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    
    @PostMapping("/addBook")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        book.setCreatedDate(LocalDate. now());
        book.setModifiedDate(LocalDate.now());
        Book savedBook = bookService.saveBook(book); 
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks(); // Retrieve all books via the service
        return ResponseEntity.ok(books);
    }

    @GetMapping("/byAuthor")
    public ResponseEntity<List<Book>> getBooksByAuthor(@RequestParam("author") String author) {
        List<Book> books = bookService.findByAuthor(author); 
        if (books.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
        }
        return ResponseEntity.ok(books);
    }
    
    
    


    
}
