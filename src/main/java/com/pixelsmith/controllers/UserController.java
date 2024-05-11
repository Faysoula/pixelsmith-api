package com.pixelsmith.controllers;

import com.pixelsmith.Models.User;
import com.pixelsmith.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User loginData) {
        return userService.authenticateUser(loginData.getEmail(), loginData.getPasswordhash())
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.UNAUTHORIZED));
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody User user) {
        try{
            User createdUser = userService.signup(user);
            return new ResponseEntity<>(createdUser, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}