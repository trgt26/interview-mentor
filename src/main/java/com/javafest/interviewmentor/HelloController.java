package com.javafest.interviewmentor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    // Define a GET route at "/hello"
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, Interview Mentor!";
    }
    @GetMapping("/")
    public String home() {
        return "Welcome to Interview Mentordsdsf API!flkdjf kljsf";
    }
}
