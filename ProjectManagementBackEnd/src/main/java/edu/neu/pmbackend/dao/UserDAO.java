/**
 * 
 */
package edu.neu.pmbackend.dao;

import java.util.List;

import edu.neu.pmbackend.entity.User;

/**
 * @author gokuljayavel
 *
 */
public interface UserDAO {

	
	 User findByUsername(String username);
	 User getById(Long id);
	 User save(User user);
	 List<User>fetchUserByManagerid(Long id);
}
