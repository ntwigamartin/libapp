package com.mcmillan.libapp.service;

import java.util.List;

import java.util.Map;
import org.springframework.stereotype.Service;

import com.mcmillan.libapp.exception.ExistingUserException;
import com.mcmillan.libapp.exception.PasswordsDoNotMatchException;
import com.mcmillan.libapp.model.User;
import com.mcmillan.libapp.repository.UserRepository;

@Service
public class UserServiceImpl implements UserServiceI{

    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User createUser(Map<String, String> params) {
        if (params.get("username").isBlank() || params.get("password").isBlank()) {
            throw new IllegalArgumentException("Null username or password");
        }

        if (userRepository.findByUsername(params.get("username")) != null) {
            throw new ExistingUserException(params.get("username"));
        }

        if (!params.get("password").equals(params.get("confirmPassword"))){
            throw new PasswordsDoNotMatchException();
        }
        
        User user = new User();
        user.setUsername(params.get("username"));
        user.setPassword(params.get("password"));
        userRepository.save(user);
        return user;
    }


    @Override
    public String login(User user) {
        if (user.getUsername().isBlank() || user.getPassword().isBlank()) {
            throw new IllegalArgumentException("Null username or password");
        }

        User existingUser = userRepository.findByUsername(user.getUsername());

        if (existingUser.getPassword().equals(user.getPassword())) {
            return "Login successful";
        } else {
            throw new IllegalArgumentException( "Invalid username or password");
        }
    }
    
}
