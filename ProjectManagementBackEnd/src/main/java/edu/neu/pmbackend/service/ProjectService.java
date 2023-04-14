/**
 * 
 */
package edu.neu.pmbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import edu.neu.pmbackend.dao.BacklogDAOImpl;
import edu.neu.pmbackend.dao.ProjectDAO;
import edu.neu.pmbackend.dao.ProjectDAOImpl;
import edu.neu.pmbackend.entity.Backlog;
import edu.neu.pmbackend.entity.Project;
import edu.neu.pmbackend.exception.ProjectIdException;


/**
 * @author gokuljayavel
 *
 */

@Service
public class ProjectService {
	
	
	
	@Autowired
	private ProjectDAOImpl projectDAO;
	
	@Autowired
	private BacklogDAOImpl backlogdao;
	
	
	public Project saveorUpdate(Project project) {
		
		
		
//
//        if(project.getId() != null){
//            Project existingProject = projectDAO.findByProjectIdentifier(project.getProjectIdentifier());
//            if(existingProject !=null &&(!existingProject.getProjectLeader().equals(username))){
//                throw new ProjectNotFoundException("Project not found in your account");
//            }else if(existingProject == null){
//                throw new ProjectNotFoundException("Project with ID: '"+project.getProjectIdentifier()+"' cannot be updated because it doesn't exist");
//            }
//        }
		
		
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			
			if(project.getId()==null){
                Backlog backlog = new Backlog();
                project.setBacklog(backlog);
                backlog.setProject(project);
                backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            }
			
			if(project.getId() != null) {
				project.setBacklog(backlogdao.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase()));
			}
			
			 return projectDAO.update(project);
			//return projectRepositry.save(project);
		} catch (Exception e) {
			e.printStackTrace();
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
