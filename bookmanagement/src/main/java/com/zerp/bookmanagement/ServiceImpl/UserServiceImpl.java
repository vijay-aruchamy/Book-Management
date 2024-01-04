package com.zerp.bookmanagement.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zerp.bookmanagement.Model.User;
import com.zerp.bookmanagement.Repository.UserRepository;
import com.zerp.bookmanagement.Service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public String loginCheck(User user1) {
        System.out.println("user1.getUserName()");
        User user = userRepository. findByuserName(user1.getUserName());
         System.out.println("loginCheck1");
        if(user.getPassword().equals(user1.getPassword()))
        return "true";
        return "false";
    }


    
}
