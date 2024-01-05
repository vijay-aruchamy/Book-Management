package com.zerp.bookmanagement.ServiceImpl;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zerp.bookmanagement.Model.Book;
import com.zerp.bookmanagement.Model.Order;
import com.zerp.bookmanagement.Model.OrderDetails;
import com.zerp.bookmanagement.Repository.OrderDetailsRepository;
import com.zerp.bookmanagement.Service.OrderDetailsService;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {
    @Autowired
     private  OrderDetailsRepository orderDetailsRepository;


      public void createOrder(Order order,Optional<Book> book)
      {
        OrderDetails orderDetails=new OrderDetails();
         orderDetails.setOrder(order);
       orderDetails.setBook(book.get());
       orderDetails.setCreatedDate(LocalDate.now());
       orderDetails.setModifiedDate(LocalDate.now());
       orderDetailsRepository.save(orderDetails);
       
      }
}
