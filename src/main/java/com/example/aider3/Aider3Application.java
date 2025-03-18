package com.example.aider3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Aider3Application {

	public static void main(String[] args) {
		SpringApplication.run(Aider3Application.class, args);
	}

	@GetMapping("/hello")
	public String hello2() {
		return "Hello from Aider3!";
	}

	@GetMapping("/echo")
	public String echo(@RequestParam String value) {
		return value;
	}
}
