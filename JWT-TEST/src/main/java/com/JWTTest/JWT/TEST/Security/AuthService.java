package com.JWTTest.JWT.TEST.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.JWTTest.JWT.TEST.DTO.LoginRequestDTO;
import com.JWTTest.JWT.TEST.DTO.LoginResponseDTO;
import com.JWTTest.JWT.TEST.DTO.SignUpResquestDTO;
import com.JWTTest.JWT.TEST.Model.Users;
import com.JWTTest.JWT.TEST.Repo.UserRepo;

@Service
public class AuthService {

	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	UserRepo repo;
	
	@Autowired
	AuthUtil authUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	public boolean signUpService(SignUpResquestDTO signUpResquestDTO) {
		Users user=Users.builder().
				username(signUpResquestDTO.getUsername())
				.password(passwordEncoder.encode(signUpResquestDTO.getPassword()))
				.build();
		repo.save(user);
		return true;
	}
	
	public LoginResponseDTO loginService(LoginRequestDTO loginRequestDTO) {
//		System.out.println(loginRequestDTO.getPassword());
//		Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword()));
//		System.out.println(loginRequestDTO.getPassword());
//		Users user=(Users) authentication.getPrincipal();
//		String token=authUtil.getAccessToken(user);
//		
//		return LoginResponseDTO.builder().token(token).userId(user.getId()).build();
		
		try {
		    Authentication authentication = authenticationManager.authenticate(
		        new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword())
		    );
		    Users user = (Users) authentication.getPrincipal();
		    String token = authUtil.getAccessToken(user);
		    return LoginResponseDTO.builder().token(token).userId(user.getId()).build();
		} catch (Exception e) {
		    throw new RuntimeException("Login failed: " + e.getMessage());
		}

	}
}
