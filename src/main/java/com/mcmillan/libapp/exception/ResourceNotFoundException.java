package com.mcmillan.libapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private String resourceName;
    private Object fieldValue;

    public ResourceNotFoundException(String resourceName, Object fieldValue) {
        super(String.format("%s with id %s not found", resourceName, fieldValue));
        this.resourceName = resourceName;
        this.fieldValue = fieldValue;
    }

    public String getResourceName() {
        return resourceName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }

}

