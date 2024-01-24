package com.zerp.bookmanagement.ServiceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
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

    public Address saveAddress(Address address) throws NotFoundException {
            if (address.getUser() != null && address.getUser().getUserId() != null) {
                Long userId = address.getUser().getUserId();
                Optional<User> userOptional = userRepository.findById(userId);
    
                if (userOptional.isPresent()) {
                    User user = userOptional.get();
                    address.setUser(user);
                    address.setModifiedDate(LocalDateTime.now());
                    address.setCreatedDate(LocalDateTime.now());
                    address.setActive(true);
                    addressRepository.save(address);
    
                    return address;
                } else {
                    throw new NotFoundException();
                }
            } else {
                throw new IllegalArgumentException("UserId cannot be null in the provided Address object.");
            }
        }

    public List<Address> showAddress(long userId) {
          
        Optional<User> user=userRepository.findById(userId);
        if(user.isPresent())
        {
            List<Address> addresses= addressRepository.findByUser(user.get());
            return addresses;
        }
        else
        throw new NullPointerException();
       
    }
}
