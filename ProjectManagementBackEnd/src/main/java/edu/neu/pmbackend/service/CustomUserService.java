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

import edu.neu.pmbackend.dao.UserDAOImpl;
import edu.neu.pmbackend.entity.User;

/**
 * @author gokuljayavel
 *
 */

@Service
public class CustomUserService implements UserDetailsService{
	
	
	@Autowired
	private UserDAOImpl userDao; 
	
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUsername(username);
		
		if (user == null ) {
			new UsernameNotFoundException(username);
		}
		return user;
	}
	
	@Transactional
	public User loadUserbyId  (Long id) {
		User user  = userDao.getById(id);
		
		return user;
	}

}
