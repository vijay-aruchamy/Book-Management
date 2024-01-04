package com.zerp.bookmanagement.Controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.zerp.bookmanagement.Model.User;
import com.zerp.bookmanagement.ServiceImpl.UserServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
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
        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);

    }

}
