package com.zerp.bookmanagement.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.zerp.bookmanagement.Model.CartDetails;
import com.zerp.bookmanagement.ServiceImpl.CartDetailsServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/cart")
public class CartDetailsController {

    @Autowired
    CartDetailsServiceImpl cartDetailsService;

    @PostMapping
    public ResponseEntity<String> getMethodName(@RequestBody Map<String, Long> data) throws Exception {
        cartDetailsService.addCart(data);

        return ResponseEntity.status(HttpStatus.CREATED).body("added to the cart");
    }

    @GetMapping
    public ResponseEntity<List<CartDetails>> showCart(@RequestBody Map<String, Long> data) {

        List<CartDetails> data1 = cartDetailsService.getCartDetails(data.get("userId"));

        return ResponseEntity.ok(data1);

    }

    @DeleteMapping
    public ResponseEntity<String> deleteItem(@RequestBody Map<String, Long> data) {
        cartDetailsService.updateCart(data.get("userId"), data.get("bookId"));
        System.out.println(data.get("id"));
        return ResponseEntity.ok("Deleted");

    }

}
