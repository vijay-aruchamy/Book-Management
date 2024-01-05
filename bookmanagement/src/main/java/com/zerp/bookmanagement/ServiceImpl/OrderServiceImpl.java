package com.zerp.bookmanagement.ServiceImpl;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zerp.bookmanagement.Model.Book;
import com.zerp.bookmanagement.Model.Order;
import com.zerp.bookmanagement.Model.OrderDetails;
import com.zerp.bookmanagement.Model.User;
import com.zerp.bookmanagement.Repository.BookRepository;
import com.zerp.bookmanagement.Repository.OrderRepository;
import com.zerp.bookmanagement.Repository.UserRepository;
import com.zerp.bookmanagement.Service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
     private  OrderRepository orderRepository;
      
     @Autowired
     private UserRepository userRepository;

     @Autowired
     private BookRepository bookRepository;

     @Autowired
     private OrderDetailsServiceImpl orderDetailsServiceImpl;

    public void orderPlace(Map<String, Long> data) {
       Order order=new Order();
       
       User user=userRepository.findByUserId(data.get("userId"));
       Optional<Book> book=bookRepository.findById(data.get("bookId"));
       order.setCreatedDate(LocalDate.now());
       order.setModifiedDate(LocalDate.now());
       order.setStatusId(1);
       order.setUser(user);
       orderRepository.save(order);
       orderDetailsServiceImpl.createOrder(order,book);
       

    }
}
