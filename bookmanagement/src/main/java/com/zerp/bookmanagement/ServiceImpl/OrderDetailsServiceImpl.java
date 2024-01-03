package com.zerp.bookmanagement.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zerp.bookmanagement.Repository.OrderDetailsRepository;
import com.zerp.bookmanagement.Service.OrderDetailsService;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {
    @Autowired
     private  OrderDetailsRepository orderDetailsRepository;
}
