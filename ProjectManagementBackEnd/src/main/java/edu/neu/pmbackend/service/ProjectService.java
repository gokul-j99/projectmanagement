/**
 * 
 */
package edu.neu.pmbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.neu.pmbackend.entity.Project;
import edu.neu.pmbackend.repositry.ProjectRepositry;

/**
 * @author gokuljayavel
 *
 */

@Service
public class ProjectService {
	
	@Autowired
	
	private ProjectRepositry projectRepositry;
	
	
	public Project saveorUpdate(Project project) {
		
		
		
		
		return projectRepositry.save(project);
		
	}
	
	

}
