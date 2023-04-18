/**
 * 
 */
package edu.neu.pmbackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import edu.neu.pmbackend.dao.UserDAO;
import edu.neu.pmbackend.dao.UserDAOImpl;
import edu.neu.pmbackend.entity.TeamMember;
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
	
	
	public User fetchbyuserName(String userName) {
		
		
		try {
			
			User user = userDao.findByUsername(userName);
			return user;
			
		} catch (Exception e) {
			throw new UserNameALreadyExistException("Username " + userName+ " doesn't  exist");
		}
		
	}
	
	
	public List<User> fetchbyManager_id(Long id ){
		
		
		try {
			
			List<User> users = userDao.fetchUserByManagerid(id);
			
			return users;
			
		} catch (Exception e) {
			throw new UserNameALreadyExistException("Manager " + id+ " doesn't  exist");
		}
		
	}
	

}
