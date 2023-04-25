/**
 * 
 */
package edu.neu.pmbackend.security;

import edu.neu.pmbackend.entity.User;
import edu.neu.pmbackend.security.SecurityConstant;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

import edu.neu.pmbackend.service.CustomUserService;

/**
 * @author gokuljayavel
 *
 */
public class JwtAuthFilter extends OncePerRequestFilter{
	
	@Autowired
	private JWTTokenProvider tokenprovider;
	
	@Autowired
	private CustomUserService customService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			
			String jwt = getJWTFromRequest(request);
			
			if (StringUtils.hasText(jwt)  && tokenprovider.validateToken(jwt)) {
				Long id = tokenprovider.getUserIdFromJWT(jwt);
				User user = customService.loadUserbyId(id);
				
				System.out.println("Got user from db");
				System.out.println(user);
				
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user , 
						null, Collections.emptyList());
				
				
				auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				System.out.println(auth.getDetails());
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
			
		} catch (Exception e) {
			
			logger.error("Couldnot set user auth",e);
			
		}
		
		
		try {
			filterChain.doFilter(request, response);
			System.out.println(filterChain);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	 private String getJWTFromRequest(HttpServletRequest request){
	        String bearerToken = request.getHeader(SecurityConstant.HEADER_STRING);
	        

	        if(StringUtils.hasText(bearerToken)&&bearerToken.startsWith(SecurityConstant.TOKEN_PREFIX)){
	        	System.out.println(bearerToken.substring(7, bearerToken.length()));
	        	 
	            return bearerToken.substring(7, bearerToken.length());
	        }

	        return null;
	    }
	

}
