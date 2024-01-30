package com.mcmillan.libapp.service;

import com.mcmillan.libapp.model.User;

public interface UserServiceI {
    User createUser(User user);
    String login(User user);
}
