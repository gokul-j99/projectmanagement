/**
 * 
 */
package edu.neu.pmbackend.security;

/**
 * @author gokuljayavel
 *
 */
public class InvalidLoginResponse {
	
	private String userName;
	private String password;
	
	
	
	public InvalidLoginResponse() {
		this.userName = "Invalid user name";
		this.password = "Invalid Password";
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
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
