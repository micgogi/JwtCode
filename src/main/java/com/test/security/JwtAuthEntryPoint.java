package com.test.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint{
public static final Logger logger=LoggerFactory.getLogger(JwtAuthEntryPoint.class); 
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException Ex)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		logger.error("Unauthorized error.message->{}",Ex.getMessage());
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Error->Unauthorized");
		
	}

}
	