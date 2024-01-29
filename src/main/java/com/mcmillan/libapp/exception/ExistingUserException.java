package com.mcmillan.libapp.exception;

public class ExistingUserException extends RuntimeException{

    public ExistingUserException(String username) {
        super(username + " is already in use");
    }

}
