package com.zerp.bookmanagement.ServiceImpl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zerp.bookmanagement.Model.Address;
import com.zerp.bookmanagement.Model.User;
import com.zerp.bookmanagement.Repository.AddressRepository;
import com.zerp.bookmanagement.Repository.UserRepository;
import com.zerp.bookmanagement.Service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;

      
         

    public Address saveAddress(Address address) {
        System.out.println(address.getAddressLine1());
        User user1 = address.getUserId();
        System.out.println(user1);
        User user = userRepository.findByUserId(user1.getUserId());
        address.setModifiedDate(LocalDateTime.now());
        address.setCreatedDate(LocalDateTime.now());
        address.setUserId(user);
        addressRepository.save(address);
        return address;
    }
}
