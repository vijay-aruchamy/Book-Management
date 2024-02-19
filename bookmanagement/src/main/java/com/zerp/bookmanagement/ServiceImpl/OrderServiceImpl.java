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
    if (!user.isPresent()) {
      System.err.println("User not found with ID: " + data.get("userId"));
      return;
    }
    Optional<Book> book = bookRepository.findById(data.get("bookId"));
    if (!book.isPresent()) {
      System.err.println("book not found with ID: " + data.get("bookId"));
      return;
    }
    Optional<Address> address = addressRepository.findById(data.get("addressId"));
    if (!address.isPresent()) {
      System.err.println("Address not found with ID: " + data.get("addressId"));
      return;
    }
    
    order.setAddressLine1(address.get().getAddressLine1()); 
    order.setAddressLine2(address.get().getAddressLine2());
    order.setDistrict(address.get().getDistrict());
    order.setState(address.get().getState());
    order.setPincode(address.get().getPincode());
    order.setCreatedDate(LocalDateTime.now());
    order.setModifiedDate(LocalDateTime.now());
    order.setUser(user.get());
    Optional<OrderStatus>  status = orderStatusRepository.findById(4);
    order.setStatusId(status.get());
    try{
    orderRepository.save(order);
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    orderDetailsServiceImpl.createOrder(order, book.get());

  }

  public void cartOrderPlace(Map<String, Long> data) {
    try {
      Optional<User> user = userRepository.findByUserId(data.get("userId"));
      if (!user.isPresent()) {
        System.err.println("User not found with ID: " + data.get("userId"));
        return;
      }
      Cart cart = cartRepository.findCartIdByuser(user.get());
      if (cart == null) {
        System.err.println("Cart not found for user: " + user.get().getUserId());
        return;
      }
      Order order = new Order();
      Optional<OrderStatus> status = orderStatusRepository.findById(4);
      Optional<Address> address = addressRepository.findById(data.get("addressId"));
      if (!address.isPresent()) {
        System.err.println("Address not found with ID: " + data.get("addressId"));
        return;
      }
      order.setAddressLine1(address.get().getAddressLine1());
      order.setAddressLine2(address.get().getAddressLine2());
      System.out.println(address.get().getAddressLine1());
      order.setDistrict(address.get().getDistrict());
      order.setState(address.get().getState());
      order.setPincode(address.get().getPincode());
      order.setStatusId(status.get());
      order.setUser(user.get());
      order.setCreatedDate(LocalDateTime.now());
      order.setModifiedDate(LocalDateTime.now());
      orderRepository.save(order);
      orderDetailsServiceImpl.createCartOrder(order, cart);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void orderConform(Long orderId) {
    Optional<Order> order = orderRepository.findById(orderId);
    Optional<OrderStatus> status = orderStatusRepository.findById(5);
    order.get().setStatusId(status.get());
    try {
      orderRepository.save(order.get());
    } catch (Exception e) {
      e.printStackTrace();
    }
    orderRepository.save(order.get());
    orderDetailsServiceImpl.orderConform(order.get());
  }

}
