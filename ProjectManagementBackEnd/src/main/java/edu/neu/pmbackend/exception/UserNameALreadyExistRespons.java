/**
 * 
 */
package edu.neu.pmbackend.exception;

/**
 * @author gokuljayavel
 *
 */
public class UserNameALreadyExistRespons {
	
	 private String username;

	    public UserNameALreadyExistRespons(String username) {
	        this.username = username;
	    }

	    public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }

}
