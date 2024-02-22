package com.mcmillan.libapp.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.List;

import java.util.Map;
import java.util.Arrays;
import org.springframework.stereotype.Service;

import com.mcmillan.libapp.Utils.HashPassword;
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
    public User createUser(Map<String, String> params) throws UnsupportedEncodingException {
        String username = params.get("username");
        String password = params.get("password");
        String confirmPassword = params.get("confirmPassword");


        if (username.isBlank() || password.isBlank()) {
            throw new IllegalArgumentException("Null username or password");
        }

        if (userRepository.findByUsername(username) != null) {
            throw new ExistingUserException(username);
        }

        if (!password.equals(confirmPassword)){
            throw new PasswordsDoNotMatchException();
        }

        byte[] salt = HashPassword.generateSalt();
        byte[] hashedPassword = HashPassword.generateHash(password, salt);

        User user = new User();
        user.setUsername(username);
        user.setPassword(Arrays.toString(hashedPassword));
        user.setSalt(salt);
        userRepository.save(user);
        return user;
    }


    @Override
    public String login(Map<String, String> params) {
        String username = params.get("username");
        String password = params.get("password");

        if (username.isBlank() || password.isBlank()) {
            throw new IllegalArgumentException("Null username or password");
        }

        User existingUser = userRepository.findByUsername(username);
        if (existingUser == null) {
            throw new IllegalArgumentException("User not found");
        }

        byte[] salt = existingUser.getSalt();
        byte[] hashedPassword = HashPassword.generateHash(password, salt);

        String storedPasswordBytes = existingUser.getPassword();

                
        // Compare the byte arrays directly
        if (storedPasswordBytes.equalsIgnoreCase(Arrays.toString(hashedPassword))) {
            return "Login successful";
        } else {
            throw new IllegalArgumentException("Invalid username or password");
        }
    }
}
