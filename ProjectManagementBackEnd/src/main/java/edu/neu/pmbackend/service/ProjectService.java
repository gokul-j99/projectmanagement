/**
 * 
 */
package edu.neu.pmbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.neu.pmbackend.dao.ProjectDAO;
import edu.neu.pmbackend.dao.ProjectDAOImpl;
import edu.neu.pmbackend.entity.Project;
import edu.neu.pmbackend.exception.ProjectIdException;
import edu.neu.pmbackend.repositry.ProjectRepositry;

/**
 * @author gokuljayavel
 *
 */

@Service
public class ProjectService {
	
	@Autowired
	
	private ProjectRepositry projectRepositry;
	
	@Autowired
	private ProjectDAOImpl projectDAO;
	
	
	public Project saveorUpdate(Project project) {
		
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			 return projectDAO.save(project);
			//return projectRepositry.save(project);
		} catch (Exception e) {
			throw new ProjectIdException("projectid" + project.getProjectIdentifier() + "already exist");
		}
		
		
	}
	
	   public Project findProjectByIdentifier(String projectId){

		   Project project = projectDAO.findByProjectIdentifier(projectId.toUpperCase());
		   
	      //  Project project = projectRepositry.findByProjectIdentifier(projectId.toUpperCase());

	        //if no project with projectID was found
	        if(project==null){
	            throw new ProjectIdException("ProjectID " + projectId + " doesn't exists");
	        }
//
//	        if(!project.getProjectLeader().equals(username)){
//	            throw new ProjectNotFoundException("Project not found in your account");
//	        }


	        return project;
	    }

	public void deleteProjectByIdentifier(String projectId) {
		try {
			System.out.println("Inside delete servcie");
		projectDAO.deleteById(Integer.parseInt(projectId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	

}
