package com.mcmillan.libapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mcmillan.libapp.exception.ExistingUserException;
import com.mcmillan.libapp.model.User;
import com.mcmillan.libapp.service.UserServiceI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/users")
public class UserController {
    
    UserServiceI userService;

    public UserController(UserServiceI userService) {
        this.userService = userService;
    }


    @PostMapping()
    public ResponseEntity<Object> createUser(@RequestBody User user) {

        try {
            return new ResponseEntity<>(userService.createUser(user), HttpStatus.OK);
        } catch (IllegalArgumentException | ExistingUserException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }
    
}
