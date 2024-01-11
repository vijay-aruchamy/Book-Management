package com.zerp.bookmanagement.ServiceImpl;

import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zerp.bookmanagement.Model.Book;
import com.zerp.bookmanagement.Model.Cart;
import com.zerp.bookmanagement.Model.Order;
import com.zerp.bookmanagement.Model.User;
import com.zerp.bookmanagement.Repository.BookRepository;
import com.zerp.bookmanagement.Repository.CartRepository;
import com.zerp.bookmanagement.Repository.OrderRepository;
import com.zerp.bookmanagement.Repository.UserRepository;
import com.zerp.bookmanagement.Service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private CartRepository cartRepository;

  @Autowired
  private OrderDetailsServiceImpl orderDetailsServiceImpl;

  public void orderPlace(Map<String, Long> data) {
    Order order = new Order();

    User user = userRepository.findByUserId(data.get("userId"));
    Optional<Book> book = bookRepository.findById(data.get("bookId"));
    order.setActive(true);
    order.setCreatedDate(LocalDateTime.now());
    order.setModifiedDate(LocalDateTime.now());
    order.setTimestamp(LocalDateTime.now());
    order.setStatusId(1);
    order.setUser(user);
    orderRepository.save(order);
    orderDetailsServiceImpl.createOrder(order, book);

  }

  public void cartOrderPlace(Long userId) {
    User user = userRepository.findByUserId(userId);
    Cart cart = cartRepository.findCartIdByuser(user);
    Order order = new Order();
    order.setUser(user);
    order.setStatusId(1);
    order.setTimestamp(LocalDateTime.now());
    order.setCreatedDate(LocalDateTime.now());
    order.setModifiedDate(LocalDateTime.now());
    order.setActive(true);
    orderRepository.save(order);
    orderDetailsServiceImpl.createCartOrder(order, cart);

  }

  public void orderConform(Long orderId) {
    Optional<Order> order = orderRepository.findById(orderId);
    order.get().setStatusId(2);
    orderRepository.save(order.get());
  }
}
