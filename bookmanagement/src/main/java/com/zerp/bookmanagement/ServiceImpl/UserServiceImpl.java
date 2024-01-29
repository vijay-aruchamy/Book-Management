package com.zerp.bookmanagement.ServiceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
        if (user.getUserName() == null || user.getPassword() == null) {
            throw new IllegalArgumentException("Username or password cannot be null");
        }
        try {
            user = userRepository.save(user);
            cartService.addUser(user);
        } catch (DataAccessException e) {

            e.printStackTrace();
        }
        return user;
    }

    public String loginCheck(User user1) {

        if (user1.getEmail() == null)
            return "Login UnSuccessful No User Found";
        else {
            User user = userRepository.findByEmail(user1.getEmail());
            if (user != null && user.getPassword().equals(user1.getPassword()))
                return "Login Successful Welcome";
        }
        return "Login UnSuccessful Password is Wrong ";
    }

    public Optional<User> findUserById(Long userId) {

        return userRepository.findById(userId);
    }

}
