/**
 * 
 */
package edu.neu.pmbackend.dto;

import edu.neu.pmbackend.entity.User;

/**
 * @author gokuljayavel
 *
 */
public class JWTLoginSuccessDTO {
	
	private boolean success;
    private String token;
    private User user;

    public JWTLoginSuccessDTO(boolean success, String token,User user) {
        this.success = success;
        this.token = token;
        this.user  =user;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
    

    /**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	@Override
    public String toString() {
        return "JWTLoginSucessReponse{" +
                "success=" + success +
                ", token='" + token + '\'' +
                '}';
    }

}
