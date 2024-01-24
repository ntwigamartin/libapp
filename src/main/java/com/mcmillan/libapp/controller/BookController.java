package com.mcmillan.libapp.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class BookController {
    
    @GetMapping("/")
    public String index() {
        return "Hello Spring";
    }
}
