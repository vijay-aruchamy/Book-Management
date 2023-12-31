package com.zerp.bookmanagement.Controller;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.zerp.bookmanagement.Model.User;
import com.zerp.bookmanagement.ServiceImpl.UserServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/adduser")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        user.setCreatedDate(LocalDate.now());
        user.setModifiedDate(LocalDate.now());
        User savedUser =userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);

    }


    
    @PostMapping("/login")
    public String login(@RequestBody User user)
    {
      return   userService.loginCheck(user);
         
    }


    @GetMapping("/getuser")
    public ResponseEntity<Optional<User>> getUser(@RequestParam("userId") Long userId)
    {
      return ResponseEntity.ok(userService.findUserById(userId));
    }
    
}
