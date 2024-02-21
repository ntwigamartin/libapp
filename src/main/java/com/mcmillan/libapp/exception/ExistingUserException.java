package com.mcmillan.libapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ExistingUserException extends RuntimeException{

    public ExistingUserException(String username) {
        super(username + " is already in use");
    }

}
