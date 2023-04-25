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

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

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
	
	@PersistenceContext
    private EntityManager entityManager;
	
	
	
	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		System.out.println("In username");
//		User user = userDao.findByUsername(username);
//		System.out.println("username");
//		System.out.println(user);
//		
//		
//		
//		if (user == null ) {
//			new UsernameNotFoundException(username);
//		}
//		return user;
//	}
	
	
	
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
        	
        	User user = userDao.findByUsername(username);
        	System.out.println(user);
        	return user;
        	
        } catch (NoResultException e) {
            throw new UsernameNotFoundException("User not found");
        }
    }
	
	@Transactional
	public User loadUserbyId  (Long id) {
		System.out.println("In userid");
		System.out.println(id);
		User user  = userDao.getById(id);
		
		return user;
	}

}
