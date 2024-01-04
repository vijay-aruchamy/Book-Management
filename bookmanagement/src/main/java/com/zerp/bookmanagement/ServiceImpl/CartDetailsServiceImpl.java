package com.zerp.bookmanagement.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zerp.bookmanagement.Repository.CartDetailsRepository;
import com.zerp.bookmanagement.Service.CartDetailsService;

@Service
public class CartDetailsServiceImpl implements CartDetailsService{
    @Autowired
     private  CartDetailsRepository cartDetailsRepository;
}
