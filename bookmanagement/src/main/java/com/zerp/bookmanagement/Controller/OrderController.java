package com.zerp.bookmanagement.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zerp.bookmanagement.Model.Order;
import com.zerp.bookmanagement.Model.User;
import com.zerp.bookmanagement.ServiceImpl.OrderDetailsServiceImpl;
import com.zerp.bookmanagement.ServiceImpl.OrderServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderServiceImpl orderServiceImpl;

    @Autowired
    OrderDetailsServiceImpl orderDetailsServiceImpl;

    @PostMapping("/orderItem")
    public ResponseEntity<String> orderProcess(@RequestBody Map<String, Long> data) {

        orderServiceImpl.orderPlace(data);
        return ResponseEntity.ok("ok");

    }

    @PostMapping("/cartOrder")
    public ResponseEntity<String> cartOrder(@RequestBody User user) throws Exception {

        orderServiceImpl.cartOrderPlace(user.getUserId());
        return ResponseEntity.ok("ok");

    }

    @GetMapping("/conform")
    public ResponseEntity<String> getMethodName(@RequestBody Order order) {
        System.out.println(order.getOrderId());
        // orderServiceImpl.orderConform(order.getOrderId());
        return ResponseEntity.ok("Conformed");
    }

    @PostMapping("/checkout")
    public Map<String, Double> checkOutProcess(@RequestBody Order order) {
        return orderDetailsServiceImpl.checkoutProcess(order);

    }

}
