package com.zerp.bookmanagement.ServiceImpl;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zerp.bookmanagement.Model.Book;
import com.zerp.bookmanagement.Model.Cart;
import com.zerp.bookmanagement.Model.CartDetails;
import com.zerp.bookmanagement.Model.Order;
import com.zerp.bookmanagement.Model.OrderDetails;
import com.zerp.bookmanagement.Repository.CartDetailsRepository;
import com.zerp.bookmanagement.Repository.CartRepository;
import com.zerp.bookmanagement.Repository.OrderDetailsRepository;
import com.zerp.bookmanagement.Repository.OrderRepository;
import com.zerp.bookmanagement.Service.OrderDetailsService;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {
  @Autowired
  private OrderDetailsRepository orderDetailsRepository;

  @Autowired
  private CartDetailsRepository cartDetailsRepository;

  @Autowired
  CartRepository cartRepository;
  @Autowired
  private OrderRepository orderRepository;

  public void createOrder(Order order, Optional<Book> book) {
    OrderDetails orderDetails = new OrderDetails();
    orderDetails.setOrder(order);
    orderDetails.setBook(book.get());
    orderDetails.setActive(true);
    orderDetails.setCreatedDate(LocalDate.now());
    orderDetails.setModifiedDate(LocalDate.now());
    orderDetailsRepository.save(orderDetails);

  }

  public void createCartOrder(Order order, Cart cart) {

    List<CartDetails> cartDetails = cartDetailsRepository.findByCart(cart);
    for (CartDetails cart1 : cartDetails) {
      OrderDetails orderDetails = new OrderDetails();
      orderDetails.setOrder(order);
      Book book = cart1.getBook();
      orderDetails.setBook(book);
      orderDetails.setCreatedDate(LocalDate.now());
      orderDetails.setActive(true);
      orderDetails.setModifiedDate(LocalDate.now());
      orderDetailsRepository.save(orderDetails);
    }

  }

  public Map<String, Double> checkoutProcess(Order order) {
    Map<String, Double> data = new HashMap();
    System.out.println(order.getOrderId());
    Optional<Order> order1 = orderRepository.findById(order.getOrderId());
    List<OrderDetails> orderDetails = orderDetailsRepository.findByOrder(order1.get());
    double total = 0;
    for (OrderDetails items : orderDetails) {
      if (items.isActive() == true) {
        Book book = items.getBook();
        data.put(book.getBookName(), book.getPrice());
        total += book.getPrice();
      }
    }
    data.put("total", total);
    return data;

  }
}
