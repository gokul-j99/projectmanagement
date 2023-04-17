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
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(ProjectIdException.class)
	public final ResponseEntity<Object> handleProjectIdException(ProjectIdException ex, WebRequest req){
		ProjectIdExceptionResponse exceptionRes = new ProjectIdExceptionResponse(ex.getMessage());
		
		return new ResponseEntity(exceptionRes, HttpStatus.BAD_REQUEST);
	}
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(UserNameALreadyExistException.class)
	public final ResponseEntity<Object> handleUserName(UserNameALreadyExistException ex, WebRequest req){
		UserNameALreadyExistRespons exceptionRes = new UserNameALreadyExistRespons(ex.getMessage());
		
		return new ResponseEntity(exceptionRes, HttpStatus.BAD_REQUEST);
	}
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(ProjectNotFoundException.class)
	public final ResponseEntity<Object> handleProjectNotFoundException(ProjectNotFoundException ex, WebRequest req){
		ProjectNotfoundExceptionResponse exceptionRes = new ProjectNotfoundExceptionResponse(ex.getMessage());
		
		return new ResponseEntity(exceptionRes, HttpStatus.BAD_REQUEST);
	}

}
