/**
 * 
 */
package edu.neu.pmbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import edu.neu.pmbackend.dao.UserDAO;
import edu.neu.pmbackend.dao.UserDAOImpl;
import edu.neu.pmbackend.entity.User;
import edu.neu.pmbackend.exception.UserNameALreadyExistException;

/**
 * @author gokuljayavel
 *
 */
@Service
public class UserService {
	

	@Autowired
	private UserDAOImpl userDao; 
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	
	public User saveUser(User user) {
		
		try {
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			System.out.println(user.getRole());
			if (StringUtils.isEmpty(user.getRole())) {
	            user.setRole("Manager");
	        }
			System.out.println(user);
			user.setUsername(user.getUsername());
			user.setConfirmPassword("");
			return userDao.save(user);
			
			//return userRepositry.save(user);
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new UserNameALreadyExistException("Username " + user.getUsername() + " already exist");
		}

		
		
		
	}




	

}
