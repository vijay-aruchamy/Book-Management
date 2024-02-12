package com.zerp.bookmanagement.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zerp.bookmanagement.Model.Order;
import com.zerp.bookmanagement.ServiceImpl.OrderDetailsServiceImpl;
import com.zerp.bookmanagement.ServiceImpl.OrderServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderServiceImpl orderServiceImpl;

    @Autowired
    OrderDetailsServiceImpl orderDetailsServiceImpl;

    @PostMapping("/order-item")
    public ResponseEntity<String> orderProcess(@RequestBody Map<String, Long> data) {
        orderServiceImpl.orderPlace(data);
        return ResponseEntity.ok("ok");
    }

    @PostMapping("/cart-order")
    public ResponseEntity<String> cartOrder(@RequestBody Map<String, Long> data) throws Exception {
        orderServiceImpl.cartOrderPlace(data);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/conform")
    public ResponseEntity<String> confirmOrder(@RequestBody Order order) {
        System.out.println(order.getOrderId());
        orderServiceImpl.orderConform(order.getOrderId());
        return ResponseEntity.ok("Confirmed");
    }

    @PostMapping("/checkout")
    public Map<String, Double> checkoutProcess(@RequestBody Order order) {
        return orderDetailsServiceImpl.checkoutProcess(order);
    }

    // @GetMapping("orderAddress")
    // public String saveOrderAddress(@RequestParam long addressId)
    // {

    // orderServiceImpl.saveAddress(addressId);
    // return null;

    // }

}
