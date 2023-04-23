/**
 * 
 */
package edu.neu.pmbackend.controller;


import java.security.Principal;

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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.neu.pmbackend.service.MapValidationErrorService;
import edu.neu.pmbackend.service.UserService;
import edu.neu.pmbackend.validator.UserValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import edu.neu.pmbackend.dto.JWTLoginSuccessDTO;
import edu.neu.pmbackend.dto.LoginRequestDTO;
import edu.neu.pmbackend.entity.Story;
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
	
	
	@PostMapping("/register/{manager_id}")
	public ResponseEntity<?> registerTeamMember(@Valid @RequestBody User user, BindingResult result,@PathVariable Long  manager_id) {
		// Validate passwords match
		
		user.setConfirmPassword(user.getPassword());
		user.setManager_id(manager_id);

		userValidator.validate(user, result);

		ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationErrorService(result);
		if (errorMap != null) {
			return errorMap;
		}

		User newUser = userService.saveUser(user);

		return new ResponseEntity<User>(newUser, HttpStatus.CREATED);

	}
	
	 @GetMapping("/{manager_id}")
	
	    public Iterable<User> getListOfStory(@PathVariable Long manager_id){
		 
		  return userService.fetchbyManager_id(manager_id);
	    }
	
	
	 @PostMapping("/login")
	    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDTO loginRequest, BindingResult result){
		 System.out.println("before error map");
		 log.info("before the error map",loginRequest);
	        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationErrorService(result);
	        if(errorMap != null) return errorMap;
	        	System.out.println("after error map");
	      //  	log.info("after the error map");
	        Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                        loginRequest.getUsername(),
	                        loginRequest.getPassword()
	                )
	        );
	        System.out.println("after authentication");
	        System.out.println(authentication.getDetails());
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        String jwt = SecurityConstant.TOKEN_PREFIX +  jwtTokenProvider.generateToken(authentication);
	        
	        User user = userService.fetchbyuserName(loginRequest.getUsername());

	        return ResponseEntity.ok(new JWTLoginSuccessDTO(true, jwt,user));
	    }
	 
	    @DeleteMapping("/{user_id}")
	    
	    public ResponseEntity<?> deleteProjectStory(@PathVariable Long user_id, Principal pr){
	       userService.deleteUserByid(user_id);

	        return new ResponseEntity<String>("User "+user_id+" was deleted successfully", HttpStatus.OK);
	    }
	
	
}

