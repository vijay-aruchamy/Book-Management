package com.zerp.bookmanagement.ServiceImpl;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zerp.bookmanagement.Model.Address;
import com.zerp.bookmanagement.Model.Book;
import com.zerp.bookmanagement.Model.Cart;
import com.zerp.bookmanagement.Model.Order;
import com.zerp.bookmanagement.Model.OrderStatus;
import com.zerp.bookmanagement.Model.User;
import com.zerp.bookmanagement.Repository.AddressRepository;
import com.zerp.bookmanagement.Repository.BookRepository;
import com.zerp.bookmanagement.Repository.CartRepository;
import com.zerp.bookmanagement.Repository.OrderRepository;
import com.zerp.bookmanagement.Repository.OrderStatusRepository;
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

  @Autowired
  private OrderStatusRepository orderStatusRepository;

  @Autowired
  private AddressRepository addressRepository;

  public void orderPlace(Map<String, Long> data) {
    Order order = new Order();

    Optional<User> user = userRepository.findByUserId(data.get("userId"));
    Optional<Book> book = bookRepository.findById(data.get("bookId"));
    Optional<Address> address = addressRepository.findById(data.get("addressId"));
    System.out.println(data.get("bookId"));
    order.setAddressLine1(address.get().getAddressLine1());
    order.setAddressLine2(address.get().getAddressLine1());
    order.setDistrict(address.get().getDistrict());
    order.setState(address.get().getState());
    order.setPincode(address.get().getPincode());
    order.setCreatedDate(LocalDateTime.now());
    order.setModifiedDate(LocalDateTime.now());
    order.setUser(user.get());
    Optional<OrderStatus> status = orderStatusRepository.findById(1);
    order.setStatusId(status.get());
    orderRepository.save(order);
    orderDetailsServiceImpl.createOrder(order, book.get());

  }

  public void cartOrderPlace(Long userId) throws Exception {
    Optional<User> user = userRepository.findByUserId(userId);
    Cart cart = cartRepository.findCartIdByuser(user.get());
    Order order = new Order();
    Optional<OrderStatus> status = orderStatusRepository.findById(1);
    order.setStatusId(status.get());
    order.setUser(user.get());
    order.setCreatedDate(LocalDateTime.now());
    order.setModifiedDate(LocalDateTime.now());
    try {
      orderRepository.save(order);
      orderDetailsServiceImpl.createCartOrder(order, cart);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  public void orderConform(Long orderId) {
    Optional<Order> order = orderRepository.findById(orderId);
    Optional<OrderStatus> status = orderStatusRepository.findById(2);
    order.get().setStatusId(status.get());
    orderRepository.save(order.get());
    orderDetailsServiceImpl.orderConform(order.get());
  }


}
