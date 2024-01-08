package com.zerp.bookmanagement.ServiceImpl;

import java.time.LocalDate;
import java.util.List;
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
import com.zerp.bookmanagement.Service.OrderDetailsService;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {
    @Autowired
     private  OrderDetailsRepository orderDetailsRepository;

     @Autowired
     private CartDetailsRepository cartDetailsRepository;

     @Autowired
     CartRepository cartRepository;


      public void createOrder(Order order,Optional<Book> book)
      {
        OrderDetails orderDetails=new OrderDetails();
         orderDetails.setOrder(order);
       orderDetails.setBook(book.get());
       orderDetails.setActive(true);
       orderDetails.setCreatedDate(LocalDate.now());
       orderDetails.setModifiedDate(LocalDate.now());
       orderDetailsRepository.save(orderDetails);
       
      }


      public void createCartOrder(Order order,Cart cart)
      {
        
     
       List<CartDetails> cartDetails=  cartDetailsRepository.findByCart(cart);
       for(CartDetails cart1:cartDetails)
       {
         OrderDetails orderDetails=new OrderDetails();
         orderDetails.setOrder(order);
         Book book= cart1.getBook();
         orderDetails.setBook(book);
         orderDetails.setCreatedDate(LocalDate.now());
         orderDetails.setActive(true);
         orderDetails.setModifiedDate(LocalDate.now());
         orderDetailsRepository.save(orderDetails);
        }
      
      }
}
