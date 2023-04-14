/**
 * 
 */
package edu.neu.pmbackend.controller;


import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.neu.pmbackend.service.MapValidationErrorService;
import edu.neu.pmbackend.service.UserService;
import edu.neu.pmbackend.validator.UserValidator;
import io.swagger.annotations.Api;
import edu.neu.pmbackend.dto.JWTLoginSuccessDTO;
import edu.neu.pmbackend.dto.LoginRequestDTO;
import edu.neu.pmbackend.entity.User;
import edu.neu.pmbackend.security.JWTTokenProvider;
import edu.neu.pmbackend.security.SecurityConstant;



/**
 * @author gokuljayavel
 *
 */
@RestController
@RequestMapping("/api/users")
@Api(value = "Project ManagementUsers", tags = "Project Management users ")
public class UserController {
	
	
	//private static Logger logger = (Logger) LogManager.getLogger(UserController.class);
	
	private static org.apache.logging.log4j.Logger log = LogManager.getLogger(UserController.class);
	
	
	
    @Autowired
    private UserValidator userValidator;
    
    @Autowired
	private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private JWTTokenProvider jwtTokenProvider;
    
    @Autowired
    private UserService userService;
    
    @Autowired 
    private AuthenticationManager authenticationManager;

	
	
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result) {
		// Validate passwords match

		userValidator.validate(user, result);

		ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationErrorService(result);
		if (errorMap != null) {
			return errorMap;
		}

		User newUser = userService.saveUser(user);

		return new ResponseEntity<User>(newUser, HttpStatus.CREATED);

	}
	
	
	 @PostMapping("/login")
	    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDTO loginRequest, BindingResult result){
		 System.out.println("before error map");
		 log.info("before the error map",loginRequest);
	        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationErrorService(result);
	        if(errorMap != null) return errorMap;
	        	System.out.println("agter error map");
	      //  	log.info("after the error map");
	        Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                        loginRequest.getUsername(),
	                        loginRequest.getPassword()
	                )
	        );
	        System.out.println(authentication.getDetails());
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        String jwt = SecurityConstant.TOKEN_PREFIX +  jwtTokenProvider.generateToken(authentication);

	        return ResponseEntity.ok(new JWTLoginSuccessDTO(true, jwt));
	    }
	
	
}

