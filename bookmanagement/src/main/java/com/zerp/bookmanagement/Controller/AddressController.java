package com.zerp.bookmanagement.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zerp.bookmanagement.Model.Address;
import com.zerp.bookmanagement.ServiceImpl.AddressServiceImpl;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressServiceImpl addressServiceImpl;
 

    @PostMapping("/saveaddress")
    public ResponseEntity<Address> saveAddress(@RequestBody Address address)
    {
       Address savedAddress= addressServiceImpl.saveAddress(address);
        return ResponseEntity.ok(savedAddress);
        
    }
    
}
