package com.zerp.bookmanagement.ServiceImpl;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zerp.bookmanagement.Model.CartDetails;
import com.zerp.bookmanagement.Model.Cart;
import com.zerp.bookmanagement.Model.User;
import com.zerp.bookmanagement.Repository.CartDetailsRepository;
import com.zerp.bookmanagement.Repository.CartRepository;
import com.zerp.bookmanagement.Service.CartDetailsService;

@Service
public class CartDetailsServiceImpl implements CartDetailsService{
    @Autowired
     private  CartDetailsRepository cartDetailsRepository;
     @Autowired
     CartRepository cartRepository;
     @Autowired
     UserServiceImpl userServiceImpl;

    public void addCart(Map<String,Long> data) {
        Optional<User> user=userServiceImpl.findUserById(data.get("userId"));
        
        CartDetails cartDetails=new CartDetails();
      Cart cart=cartRepository.findCartIdByuser(user);
       cartDetails.setCart(cart);
        // cartDetails.setUserId(data.get("userId"));
        cartDetailsRepository.save(cartDetails);
    }
}
