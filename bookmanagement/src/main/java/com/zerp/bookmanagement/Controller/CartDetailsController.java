package com.zerp.bookmanagement.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.zerp.bookmanagement.Model.Cart;
import com.zerp.bookmanagement.ServiceImpl.CartDetailsServiceImpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/cart")
public class CartDetailsController {
 
    @Autowired
    CartDetailsServiceImpl cartDetailsService;


    @GetMapping("/addCart")
    public ResponseEntity<String> getMethodName(@RequestBody Map<String,Long> data) {
        cartDetailsService.addCart(data);
         
        
        return ResponseEntity.status(HttpStatus.CREATED).body("added to the cart");
    }


    public void setCart(Cart cart) {
    }
    
    
}
