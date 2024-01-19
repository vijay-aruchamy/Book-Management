package com.zerp.bookmanagement.ServiceImpl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zerp.bookmanagement.Model.Address;
import com.zerp.bookmanagement.Model.User;
import com.zerp.bookmanagement.Repository.AddressRepository;
import com.zerp.bookmanagement.Service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;
    public Address saveAddress(Address address,User user) {
        address.setUserId(user);
        address.setModifiedDate(LocalDateTime.now());
        address.setCreatedDate(LocalDateTime.now());
        addressRepository.save(address);
        return address;
    }
}
