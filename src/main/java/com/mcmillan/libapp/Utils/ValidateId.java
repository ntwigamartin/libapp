package com.mcmillan.libapp.Utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;

public class ValidateId {
    public static ResponseEntity<Object> validate(String idString) {
        if (!StringUtils.hasText(idString)) {
            return new ResponseEntity<>("ID cannot be blank", HttpStatus.BAD_REQUEST);
        }

        try {
            Long id = Long.parseLong(idString);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>("Invalid Id format", HttpStatus.BAD_REQUEST);
        }

        return null;
    }
}
