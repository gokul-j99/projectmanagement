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
public class ProjectIdException extends RuntimeException{

	private static final long serialVersionUID = 1061062553594309584L;

	public ProjectIdException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
}
