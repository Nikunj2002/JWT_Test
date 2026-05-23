package com.JWTTest.JWT.TEST.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	@GetMapping("/auth/hello")
	public String sayHello() {
		return "Hello";
	}
	@GetMapping("/jwt/check/after-login")
	public String JWTCHECK() {
		System.out.println("Test");
		return "JWT validate successifully";
	}
}
