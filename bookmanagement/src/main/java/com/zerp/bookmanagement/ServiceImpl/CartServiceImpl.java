package com.zerp.bookmanagement.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zerp.bookmanagement.Repository.CartRepository;
import com.zerp.bookmanagement.Service.CartService;

@Service
public class CartServiceImpl implements CartService{
    @Autowired
     private  CartRepository cartRepository;
}
