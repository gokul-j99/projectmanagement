/**
 * 
 */
package edu.neu.pmbackend.dao;

import edu.neu.pmbackend.entity.User;

/**
 * @author gokuljayavel
 *
 */
public interface UserDAO {

	
	 User findByUsername(String username);
	 User getById(Long id);
}
