package com.zerp.bookmanagement.ServiceImpl;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zerp.bookmanagement.Model.User;
import com.zerp.bookmanagement.Repository.UserRepository;
import com.zerp.bookmanagement.Service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartServiceImpl cartService;


   

    public User addUser(User user) {
       
       user = userRepository.save(user);
        cartService.addUser(user);
        return user;
    }

    public String loginCheck(User user1) {
        
        if(user1.getUserName()==null)
        return "false";
        else
        {
        User user = userRepository. findByuserName(user1.getUserName());
        if(user!=null && user.getPassword().equals(user1.getPassword()))
        return "true";
        }
        return "false";
    }


    public Optional<User> findUserById(Long userId)
    {
   
   return userRepository.findById(userId);
    }


    
}
