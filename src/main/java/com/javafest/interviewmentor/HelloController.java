package com.javafest.interviewmentor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    // Define a GET route at "/hello"
    @GetMapping("/")
    public String sayHello() {
        return "Hello, Interview Mentor!";
    }
}
