package com.zerp.bookmanagement.ServiceImpl;

import java.nio.file.attribute.UserPrincipalNotFoundException;
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
    try {
      Optional<User> user = userRepository.findByUserId(data.get("userId"));
      Cart cart = cartRepository.findCartIdByuser(
          user.orElseThrow(() -> new UserPrincipalNotFoundException("User not found with ID: " + data.get("userId"))));
      Optional<Book> book = bookServiceImpl.findBookById(data.get("bookId"));
      CartDetails existingCartDetails = cartDetailsRepository.findByCartAndBook(cart, book);

      if (existingCartDetails != null) {
        if (existingCartDetails.getQuantity() > book.get().getQuantity()) {
          throw new Exception("The book's quantity is not sufficient");
        } else {
          existingCartDetails.setQuantity(existingCartDetails.getQuantity() + 1);
          existingCartDetails.setPrice(existingCartDetails.getQuantity() * book.get().getPrice());
          existingCartDetails.setModifiedDate(LocalDateTime.now());
          cartDetailsRepository.save(existingCartDetails);
        }
      } else {
        CartDetails cartDetails = new CartDetails();
        if (cartDetails.getQuantity() > book.get().getQuantity()) {
          throw new Exception("The book's quantity is not sufficient");
        } else {
          cartDetails.setBook(book.get());
          cartDetails.setCart(cart);
          cartDetails.setQuantity(1);
          cartDetails.setPrice(book.get().getPrice());
          cartDetails.setCreatedDate(LocalDateTime.now());
          cartDetails.setModifiedDate(LocalDateTime.now());
          cartDetailsRepository.save(cartDetails);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public List<CartDetails> getCartDetails(Long userId) {
    Optional<User> user = userRepository.findByUserId(userId);
    if (user.isPresent()) {
      Cart cart = cartRepository.findCartIdByuser(user.get());
      if (cartDetailsRepository.findByCart(cart) != null)
        return cartDetailsRepository.findByCart(cart);
    }
    return null;

  }

  public void updateCart(Long userId, Long bookId) {
    Optional<User> user = userRepository.findById(userId);
    Optional<Book> book = bookRepository.findById(bookId);
    if (user.isPresent()) {
      Cart cart = cartRepository.findCartIdByuser(user.get());
      CartDetails cartDetails = cartDetailsRepository.findByCartAndBook(cart, book);
      if (cartDetails.getQuantity() > 2) {
        cartDetails.setQuantity(cartDetails.getQuantity() - 1);
        cartDetails.setPrice(cartDetails.getPrice() - book.get().getPrice());
        cartDetailsRepository.save(cartDetails);
      } else
        cartDetailsRepository.deleteByCartAndBook(cart, book.get());
    } else
      return;
  }

}
