package com.zerp.bookmanagement.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zerp.bookmanagement.Model.Book;
import com.zerp.bookmanagement.Model.Cart;
import com.zerp.bookmanagement.Model.CartDetails;

@Repository
public interface CartDetailsRepository extends JpaRepository<CartDetails, Long> {

    List<CartDetails> findByCart(Cart cart);


    void deleteByBook(Book book);





    void deleteByCartAndBook(Cart cart, Book book);


    CartDetails findByCartAndBook(Cart cart, Optional<Book> book);


   
}

    

    

