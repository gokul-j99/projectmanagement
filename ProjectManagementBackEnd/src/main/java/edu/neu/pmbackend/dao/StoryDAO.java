/**
 * 
 */
package edu.neu.pmbackend.dao;

import java.util.List;

import edu.neu.pmbackend.entity.Story;

/**
 * @author gokuljayavel
 *
 */
public interface StoryDAO {
	
	
	 List<Story> findByProjectIdentifierOrderByPriority(String id);

	 Story findByProjectSequence(String sequence);

	 Story save(Story story);

	 void delete(Story story);

}
