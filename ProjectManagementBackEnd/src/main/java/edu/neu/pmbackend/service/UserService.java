/**
 * 
 */
package edu.neu.pmbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.neu.pmbackend.repositry.UserRepositry;

/**
 * @author gokuljayavel
 *
 */
@Service
public class UserService {
	
	@Autowired
	private UserRepositry userRepositry;
	

}
