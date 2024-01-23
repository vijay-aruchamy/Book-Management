package com.zerp.bookmanagement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zerp.bookmanagement.Model.Book;
import com.zerp.bookmanagement.Model.Order;
import com.zerp.bookmanagement.Model.OrderDetails;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {

    List<OrderDetails> findByOrder(Order order);
    // You can add custom queries or methods specific to OrderDetails if needed

    List<Book> findBookById(Long id);

}
