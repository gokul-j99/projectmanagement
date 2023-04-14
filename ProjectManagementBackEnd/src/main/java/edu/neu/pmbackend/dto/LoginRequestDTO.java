/**
 * 
 */
package edu.neu.pmbackend.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author gokuljayavel
 *
 */
public class LoginRequestDTO {
	@NotBlank(message = "username is required")
	private String username;
	@NotBlank(message = "Password field is required")
    private String password;
	
	  @JsonCreator
	    public LoginRequestDTO(@JsonProperty("username") String username,
	                           @JsonProperty("password") String password) {
	        this.username = username;
	        this.password = password;
	    }
	
	
	
	/**
	 * @param username
	 */
	public LoginRequestDTO(String username) {
		this.username = username;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
