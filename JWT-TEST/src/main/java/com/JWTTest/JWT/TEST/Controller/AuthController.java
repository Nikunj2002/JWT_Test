package com.JWTTest.JWT.TEST.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JWTTest.JWT.TEST.DTO.LoginRequestDTO;
import com.JWTTest.JWT.TEST.DTO.LoginResponseDTO;
import com.JWTTest.JWT.TEST.DTO.SignUpResquestDTO;
import com.JWTTest.JWT.TEST.Security.AuthService;

@RestController()
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	AuthService authService;
	@PostMapping("/signUp")
	public boolean signUp(@RequestBody SignUpResquestDTO signUpResquestDTO) {
		return authService.signUpService(signUpResquestDTO);
	}
	
	@PostMapping("/login")
	public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO) {
		return authService.loginService(loginRequestDTO);
	}
}
