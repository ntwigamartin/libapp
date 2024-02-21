package com.mcmillan.libapp.service;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import com.mcmillan.libapp.model.User;

public interface UserServiceI {
    User createUser(Map<String, String> params) throws UnsupportedEncodingException;
    String login(Map<String, String> params);
}
