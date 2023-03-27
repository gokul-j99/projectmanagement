/**
 * 
 */
package edu.neu.pmbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author gokuljayavel
 *
 */
@ControllerAdvice
@RestController
public class CustomReponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler
	public final ResponseEntity<Object> handleProjectIdException(ProjectIdException ex, WebRequest req){
		ProjectIdExceptionResponse exceptionRes = new ProjectIdExceptionResponse(ex.getMessage());
		
		return new ResponseEntity(exceptionRes, HttpStatus.BAD_REQUEST);
	}

}
