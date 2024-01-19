package com.zerp.bookmanagement.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zerp.bookmanagement.Model.Address;
import com.zerp.bookmanagement.Model.User;
import com.zerp.bookmanagement.ServiceImpl.AddressServiceImpl;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressServiceImpl addressServiceImpl;
 

    @PostMapping("/saveaddress")
    public ResponseEntity<Address> saveAddress(@RequestBody List<Object> data)
    {
        Address address=(Address) data.get(1);
        User user=(User) data.get(0);
       Address savedAddress= addressServiceImpl.saveAddress(address,user);
        return ResponseEntity.ok(savedAddress);
        
    }
    
}
