package com.zerp.bookmanagement.ServiceImpl;

import java.time.LocalDate;
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

    CartDetails cartDetails = new CartDetails();
    Cart cart = cartRepository.findCartIdByuser(user);
    Optional<Book> book = bookServiceImpl.findBookById(data.get("bookId"));
    cartDetails.setBook(book.get());
    cartDetails.setCart(cart);
    cartDetails.setCreatedDate(LocalDate.now());
    cartDetails.setModifiedDate(LocalDate.now());
    cartDetailsRepository.save(cartDetails);
  }

  public List<CartDetails> getCartDetails(Long userId) {
    User user = userRepository.findByUserId(userId);
    Cart cart = cartRepository.findCartIdByuser(user);
    if (cartDetailsRepository.findByCart(cart) != null)
      return cartDetailsRepository.findByCart(cart);
    return null;

  }

  // public void updateCart(Long userId, Long bookId) {
  //   System.out.println(bookId);
  //   Optional<Book> book = bookRepository.findById(bookId);
  //   Optional<User> user = userRepository.findById(userId);
  //   Cart cart = cartRepository.findCartIdByuser(user.get());
  //   List<CartDetails> cartDetails = cartDetailsRepository.findByCart(cart);
  //   for (CartDetails items : cartDetails) {
  //     items.;
  //   }
  // }

}
