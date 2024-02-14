package com.zerp.bookmanagement.ServiceImpl;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
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
import com.zerp.bookmanagement.Repository.BookRepository;
import com.zerp.bookmanagement.Repository.CartDetailsRepository;
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
  private OrderRepository orderRepository;
  @Autowired
  private BookRepository bookRepository;

  public void createOrder(Order order, Book book) {
    OrderDetails orderDetails = new OrderDetails();
    orderDetails.setOrder(order);
    orderDetails.setBook(book);
    orderDetails.setCreatedDate(LocalDateTime.now());
    orderDetails.setModifiedDate(LocalDateTime.now());
    orderDetailsRepository.save(orderDetails);

  }

  public void createCartOrder(Order order, Cart cart) throws Exception {

    List<CartDetails> cartDetails = cartDetailsRepository.findByCart(cart);
    for (CartDetails cart1 : cartDetails) {
      OrderDetails orderDetails = new OrderDetails();
      orderDetails.setOrder(order);
      Book book = cart1.getBook();
      if (book.getQuantity() > cart1.getQuantity()) {
        orderDetails.setBook(book);
        orderDetails.setQuantity(cart1.getQuantity());
        orderDetails.setCreatedDate(LocalDateTime.now());
        orderDetails.setModifiedDate(LocalDateTime.now());
        orderDetailsRepository.save(orderDetails);
      } else
        throw new Exception("The required amount of book is out of stock");
    }

  }

  public Map<String, Double> checkoutProcess(Order order) {
    LinkedHashMap<String, Double> data = new LinkedHashMap();
    System.out.println(order.getOrderId());
    Optional<Order> order1 = orderRepository.findById(order.getOrderId());
    List<OrderDetails> orderDetails = orderDetailsRepository.findByOrder(order1.get());
    double total = 0;
    for (OrderDetails items : orderDetails) {
      Book book = items.getBook();
      data.put(book.getBookName() + "-Quantity", (double) items.getQuantity());
      data.put(book.getBookName(), book.getPrice() * items.getQuantity());
      total += book.getPrice() * items.getQuantity();
    }
    data.put("total", total);
    return data;

  }

  public void orderConform(Order order) {
    List<OrderDetails> orderDetails = orderDetailsRepository.findByOrder(order);
    for (OrderDetails orderDetails2 : orderDetails) {
      Book book = orderDetails2.getBook();
      int quantity = orderDetails2.getQuantity();
      int bookQuantity = book.getQuantity();
      book.setQuantity(bookQuantity - quantity);
      bookRepository.save(book);
    }
  }

}
 