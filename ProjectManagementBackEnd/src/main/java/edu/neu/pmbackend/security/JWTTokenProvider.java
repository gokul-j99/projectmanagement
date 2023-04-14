/**
 * 
 */
package edu.neu.pmbackend.security;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import edu.neu.pmbackend.entity.User;

import static edu.neu.pmbackend.security.SecurityConstant.EXPIRATION_TIME;

import static edu.neu.pmbackend.security.SecurityConstant.SECRET;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 * @author gokuljayavel
 *
 */

@Component
public class JWTTokenProvider {
	
	public String generateToken(Authentication auth) {
		
		User user = (User) auth.getPrincipal();
		
		Date now = new Date();
		
		Date expiry = new Date(now.getTime() + EXPIRATION_TIME);
		
		String userId = Long.toString(user.getId());
		
		 System.out.println(now);
	        
	        System.out.println(expiry);
		
		
		// HMAC-SHA512 is a symmetric signing algorithm, which means that the same secret key is used for both signing and verifying the JWT.
        //The secret key should be kept confidential and not exposed publicly.
		

        Map<String,Object> claim = new HashMap<>();
        claim.put("id", (Long.toString(user.getId())));
        claim.put("username", user.getUsername());
        claim.put("fullName", user.getFullName());
        claim.put("role", user.getRole());
		
		
		return Jwts.builder().setSubject(userId).setClaims(claim).setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
		
		
	
	}
	
	
	public boolean validateToken(String token) {
		try {
			
			Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
			return true;
			
		} catch (ExpiredJwtException e) {
			System.out.println("Expired JWT token");	
		}catch (UnsupportedJwtException e){
            System.out.println("Unsupported JWT token");
        }catch (IllegalArgumentException e){
            System.out.println("JWT claims string is empty");
        }
		catch (Exception e) {	
			System.out.println("Invalidate JWT token");
		}
		return false;
	}
	
	 public Long getUserIdFromJWT(String token){
	        Claims claims = Jwts.parser().setSigningKey(SecurityConstant.SECRET).parseClaimsJws(token).getBody();
	        String id = (String)claims.get("id");

	        return Long.parseLong(id);
	    }
	 
	 
	 public String getUserRoleFromJWT(String token){
	        Claims claims = Jwts.parser().setSigningKey(SecurityConstant.SECRET).parseClaimsJws(token).getBody();
	        String role = (String)claims.get("role");

	        return role;
	    }

}
