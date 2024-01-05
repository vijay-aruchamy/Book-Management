package com.zerp.bookmanagement.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zerp.bookmanagement.ServiceImpl.OrderServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
OrderServiceImpl orderServiceImpl;

@PostMapping("/conform")
public ResponseEntity<String> orderProcess(@RequestBody Map<String,Long> data)
{

    orderServiceImpl.orderPlace(data);
    return ResponseEntity.status(100).body("sucess");
    
}
    
}
