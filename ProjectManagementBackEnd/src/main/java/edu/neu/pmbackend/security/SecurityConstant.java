/**
 * 
 */
package edu.neu.pmbackend.security;

/**
 * @author gokuljayavel
 *
 */

public class SecurityConstant {

    public static final String SIGN_UP_URLS = "/api/users/**";
    public static final String SECRET ="SecretKeyToGenJWTs";
    public static final String TOKEN_PREFIX= "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final long EXPIRATION_TIME = 1000_000; 
}

