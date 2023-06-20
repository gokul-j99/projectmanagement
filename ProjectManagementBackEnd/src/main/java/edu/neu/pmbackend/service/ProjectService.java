/**
 * 
 */
package edu.neu.pmbackend.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import edu.neu.pmbackend.dao.BacklogDAOImpl;
import edu.neu.pmbackend.dao.ProjectDAO;
import edu.neu.pmbackend.dao.ProjectDAOImpl;
import edu.neu.pmbackend.dao.UserDAOImpl;
import edu.neu.pmbackend.entity.Backlog;
import edu.neu.pmbackend.entity.Project;
import edu.neu.pmbackend.entity.Story;
import edu.neu.pmbackend.entity.User;
import edu.neu.pmbackend.exception.ProjectDeleteException;
import edu.neu.pmbackend.exception.ProjectIdException;
import edu.neu.pmbackend.exception.ProjectNotFoundException;


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
	
	@Autowired
	private UserDAOImpl userDao;
	
	@Autowired
	private StoryService storyService;
	
	
	public Project saveorUpdate(Project project, String userName) {
		
		
		

        if(project.getId() != null){
            Project existingProject = projectDAO.findByProjectIdentifier(project.getProjectIdentifier());
            if(existingProject !=null &&(!existingProject.getProjectLeader().equals(userName))){
                throw new ProjectNotFoundException("Project not found in your account");
            }else if(existingProject == null){
                throw new ProjectNotFoundException("Project with ID: '"+project.getProjectIdentifier()+"' cannot be updated because it doesn't exist");
            }
        }
		
		
		try {
			User user = userDao.findByUsername(userName);
			
			if(!user.getRole().equals("Manager")) {
				throw new ProjectNotFoundException("You don't have acces to create project");
			}
			
			project.setUser(user);
			project.setProjectLeader(userName);
			
			System.out.println(project);
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			
			if(project.getId()==null){
				project.setCreated_At(new Date() );
                Backlog backlog = new Backlog();
                project.setBacklog(backlog);
                backlog.setProject(project);
                backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            }
			
			if(project.getId() != null) {
				 project.setUpdated_At(new Date());
				project.setBacklog(backlogdao.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase()));
			}
			
			 return projectDAO.saveOrupdate(project);
		}
		
		catch (ProjectNotFoundException e) {
		    throw e;
		} 
		
		
		catch (Exception e) {
			e.printStackTrace();
			throw new ProjectIdException("projectid" + project.getProjectIdentifier() + "already exist");
		}
		
		
	}
	
	   public Project findProjectByIdentifier(String projectId, String userName){

		   Project project = projectDAO.findByProjectIdentifier(projectId.toUpperCase());
		   
		   User user = userDao.findByUsername(userName);

	        //if no project with projectID was found
	        if(project==null){
	            throw new ProjectIdException("ProjectID " + projectId + " doesn't exists");
	        }
	        
	        if (user.getRole().equals("Manager")) {
	        	  if(!project.getProjectLeader().equals(userName)){
	  	            throw new ProjectNotFoundException("Project not found in your account");
	  	        }
	        	
	        }

	      


	        return project;
	    }

	public void deleteProjectByIdentifier(String projectId, String user) {
		try {
			
			User userD = userDao.findByUsername(user);
			
			
			if(!userD.getRole().equals("Manager")) {
				throw new ProjectNotFoundException("You don't have acces to delete project");
			}
			
			List<Story> storylist = (List<Story>) storyService.getStoryById(projectId, user);
			
			if(storylist.size() > 0) {
				throw new ProjectDeleteException("You have existing stories in project delete stories first to delete the project");
			}
			
			findProjectByIdentifier(projectId, user);
			
			System.out.println("Inside delete servcie");
		projectDAO.deleteById(projectId);
		
		} 
		
		catch (ProjectDeleteException e) {
		    throw e;
		} 
		
		catch (ProjectNotFoundException e) {
		    throw e;
		} 
		
		catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	public List<Project> getAllProject(Long user_id){
		System.out.println("Inside Prject all");
		
		
		User user = userDao.getById(user_id);
		
		
		
		if(user.getRole().equals("Manager")) {
			System.out.println("Inside manager");
			return projectDAO.findAllByUserid(user_id);
		}
		System.out.println(user);
		return projectDAO.findAllByUserid(user.getManager_id());
	}
	
	

}
