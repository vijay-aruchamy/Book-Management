package com.zerp.bookmanagement.ServiceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zerp.bookmanagement.Model.CartDetails;
import com.zerp.bookmanagement.Model.Book;
import com.zerp.bookmanagement.Model.Cart;
import com.zerp.bookmanagement.Model.User;
import com.zerp.bookmanagement.Repository.BookRepository;
import com.zerp.bookmanagement.Repository.CartDetailsRepository;
import com.zerp.bookmanagement.Repository.CartRepository;
import com.zerp.bookmanagement.Repository.UserRepository;
import com.zerp.bookmanagement.Service.CartDetailsService;

@Service
public class CartDetailsServiceImpl implements CartDetailsService {
  @Autowired
  private CartDetailsRepository cartDetailsRepository;
  @Autowired
  CartRepository cartRepository;
  @Autowired
  UserServiceImpl userServiceImpl;
  @Autowired
  BookServiceImpl bookServiceImpl;
  @Autowired
  UserRepository userRepository;
  @Autowired
  BookRepository bookRepository;

  public void addCart(Map<String, Long> data) {
    User user = userRepository.findByUserId(data.get("userId"));
    Cart cart = cartRepository.findCartIdByuser(user);
    Optional<Book> book = bookServiceImpl.findBookById(data.get("bookId"));
    CartDetails existingCartDetails = cartDetailsRepository.findByCartAndBook(cart, book);
    if (existingCartDetails != null) {
      existingCartDetails.setQuantity(existingCartDetails.getQuantity() + 1);
      existingCartDetails.setPrice(existingCartDetails.getQuantity() * book.get().getPrice());
      existingCartDetails.setModifiedDate(LocalDateTime.now());
      cartDetailsRepository.save(existingCartDetails);
    } else {
      CartDetails cartDetails = new CartDetails();
      cartDetails.setBook(book.get());
      cartDetails.setCart(cart);
      cartDetails.setQuantity(1);
      cartDetails.setPrice(book.get().getPrice());
      cartDetails.setCreatedDate(LocalDateTime.now());
      cartDetails.setModifiedDate(LocalDateTime.now());
      cartDetailsRepository.save(cartDetails);
    }
  }

  public List<CartDetails> getCartDetails(Long userId) {
    User user = userRepository.findByUserId(userId);
    Cart cart = cartRepository.findCartIdByuser(user);
    if (cartDetailsRepository.findByCart(cart) != null)
      return cartDetailsRepository.findByCart(cart);
    return null;

  }


}
