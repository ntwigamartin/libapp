package com.mcmillan.libapp.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    public User createUser(User user) {

        if (user.getUsername().isBlank() || user.getPassword().isBlank()) {
            throw new IllegalArgumentException("Null username or password");
        }

        List<User> users = userRepository.findAll();

        for (User existingUser : users) {
            if (existingUser.getUsername().equalsIgnoreCase(user.getUsername())) {
                throw new ExistingUserException(existingUser.getUsername());
            }
        }

        if (!user.getPassword().equals(user.getConfirmPassword())){
            throw new PasswordsDoNotMatchException();
        }
        
        return userRepository.save(user);

    }
    
}
