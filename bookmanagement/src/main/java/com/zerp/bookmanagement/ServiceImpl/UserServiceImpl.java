package com.zerp.bookmanagement.ServiceImpl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.zerp.bookmanagement.Model.User;
import com.zerp.bookmanagement.Repository.UserRepository;
import com.zerp.bookmanagement.Security.JwtService;

@Service
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartServiceImpl cartService;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    JwtService jwtService;

    
    public User addUser(User user) {
        try {
            user.setPassword(encoder.encode(user.getPassword()));
            user.setCreatedDate(LocalDateTime.now());
            user.setModifiedDate(LocalDateTime.now());
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

            if (user != null && encoder.matches(user1.getPassword(), user.getPassword())) {
                return "BearerToken = " + jwtService.generateToken(user.getEmail());
            }
        }
        return "Login UnSuccessful Password is Wrong ";
    }

    public Optional<User> findUserById(Long userId) {

        return userRepository.findById(userId);
    }

}
