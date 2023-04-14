/**
 * 
 */
package edu.neu.pmbackend.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

/**
 * @author gokuljayavel
 *
 */
@Component
public class JwtAuthentication implements AuthenticationEntryPoint{

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		// TODO Auto-generated method stub
		InvalidLoginResponse inv = new InvalidLoginResponse();
		String jsonLogin_response = new Gson().toJson(inv);
		
		response.setContentType("application/json");
		response.setStatus(401);
		response.getWriter().print(jsonLogin_response);
	}
	

}
