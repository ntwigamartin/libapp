package com.mcmillan.libapp.service;

import java.util.Map;

import com.mcmillan.libapp.model.User;

public interface UserServiceI {
    User createUser(Map<String, String> params);
    String login(User user);
}
