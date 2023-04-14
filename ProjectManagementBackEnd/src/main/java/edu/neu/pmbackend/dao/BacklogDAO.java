/**
 * 
 */
package edu.neu.pmbackend.dao;

import edu.neu.pmbackend.entity.Backlog;

/**
 * @author gokuljayavel
 *
 */
public interface BacklogDAO {
	
	public Backlog save(Backlog backlog);
	public Backlog findById(Long id) ;
	public Backlog findByProjectIdentifier(String identifier);
	 public void delete(Backlog backlog);

}
