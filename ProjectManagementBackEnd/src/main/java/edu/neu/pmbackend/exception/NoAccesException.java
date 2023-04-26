/**
 * 
 */
package edu.neu.pmbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author gokuljayavel
 *
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoAccesException extends RuntimeException{
	
	
	private static final long serialVersionUID = 1091062553594388584L;

	/**
	 * 
	 */
	public NoAccesException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	

}
