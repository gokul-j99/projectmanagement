/**
 * 
 */
package edu.neu.pmbackend.repositry;

import org.springframework.data.repository.CrudRepository;

import edu.neu.pmbackend.entity.User;

/**
 * @author gokuljayavel
 *
 */
public interface UserRepositry extends CrudRepository<User, Long>{
	
	 User findByUsername(String username);
	 User getById(Long id);
	 

}
