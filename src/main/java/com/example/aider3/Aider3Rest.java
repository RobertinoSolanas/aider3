package com.example.aider3;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Aider3Rest {

    @GetMapping("/hello3")
    public String hello3() {
        return "Hello from Aider3!";
    }

    @GetMapping("/echo")
    public String echo(@RequestParam String value) {
        return value;
    }
}
