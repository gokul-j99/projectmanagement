/**
 * 
 */
package edu.neu.pmbackend.dao;

import edu.neu.pmbackend.entity.TeamMember;


/**
 * @author gokuljayavel
 *
 */
public interface TeammemberDAO {
	
	
	 TeamMember findByUsername(String username);
	 TeamMember getById(Long id);
	 TeamMember save(TeamMember user);

}
