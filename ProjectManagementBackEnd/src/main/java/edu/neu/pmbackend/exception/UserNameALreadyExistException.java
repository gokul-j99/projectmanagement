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
public class UserNameALreadyExistException extends RuntimeException{
	
	private static final long serialVersionUID = 1061062553594309184L;
	
	public UserNameALreadyExistException(String message) {
		super(message);
	}

}
