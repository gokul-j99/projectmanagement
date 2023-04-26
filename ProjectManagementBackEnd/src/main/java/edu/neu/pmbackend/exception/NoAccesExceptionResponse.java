/**
 * 
 */
package edu.neu.pmbackend.exception;

/**
 * @author gokuljayavel
 *
 */
public class NoAccesExceptionResponse {
	
	
private String access;
	
	public NoAccesExceptionResponse (String access ) {
		this.access = access;
	}

	/**
	 * @return the access
	 */
	public String getAccess() {
		return access;
	}

	/**
	 * @param access the access to set
	 */
	public void setAccess(String access) {
		this.access = access;
	}




}
