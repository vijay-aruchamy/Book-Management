package com.zerp.bookmanagement.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zerp.bookmanagement.Model.Address;
import com.zerp.bookmanagement.ServiceImpl.AddressServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressServiceImpl addressServiceImpl;
 

    @PostMapping("/saveaddress")
    public ResponseEntity<Address> saveAddress(@RequestBody Address address) throws NotFoundException
    {
       Address savedAddress= addressServiceImpl.saveAddress(address);
        return ResponseEntity.ok(savedAddress);
        
    }


    @GetMapping("/showAddress")
    public List<Address> showAddresses(@RequestParam long userId) {
        return  addressServiceImpl.showAddress(userId);
        
    }
    
    
}
