package com.zerp.bookmanagement.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zerp.bookmanagement.Repository.AddressRepository;
import com.zerp.bookmanagement.Service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
     private   AddressRepository addressRepository;
}
