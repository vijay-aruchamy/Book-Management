package com.zerp.bookmanagement.ServiceImpl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zerp.bookmanagement.Model.Cart;
import com.zerp.bookmanagement.Model.User;
import com.zerp.bookmanagement.Repository.CartRepository;
import com.zerp.bookmanagement.Service.CartService;

@Service
public class CartServiceImpl implements CartService{
    @Autowired
     private  CartRepository cartRepository;

     
    
    public void addUser(User user) {
        Cart cart=new Cart();
        cart.setUserId(user);
        cart.setCreatedDate(LocalDate.now());
        cart.setModifiedDate(LocalDate.now());
        cart.setActive(true);
        cartRepository.save(cart);
        
    }
}
