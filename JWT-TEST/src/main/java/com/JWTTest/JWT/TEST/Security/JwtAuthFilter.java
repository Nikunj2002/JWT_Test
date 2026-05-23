package com.JWTTest.JWT.TEST.Security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.JWTTest.JWT.TEST.Model.Users;
import com.JWTTest.JWT.TEST.Repo.UserRepo;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter{

	@Autowired
	AuthUtil authUtil;
	@Autowired
	UserRepo userRepo;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String requestTokenHeader=request.getHeader("Authentication");
			if(requestTokenHeader==null || !requestTokenHeader.startsWith("Bearer")) {
				filterChain.doFilter(request, response);
				return;
			}
			String token=requestTokenHeader.split("Bearer ")[1];
			String username=authUtil.getUsernameFromToken(token);
			if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
				Users user=userRepo.findByUsername(username).orElseThrow();
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(user, null,user.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
			filterChain.doFilter(request, response);
		}catch(Exception ex) {
//			HandlerExceptionResolver handlerExceptionResolver = HandlerExceptionResolver; resolveException(request,response,null,ex);
		}
	}

}
