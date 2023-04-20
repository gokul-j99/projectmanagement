/**


 * 
 */
package edu.neu.pmbackend.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.neu.pmbackend.dao.BacklogDAOImpl;
import edu.neu.pmbackend.dao.StoryDAOImpl;
import edu.neu.pmbackend.dto.StoryStatus;
import edu.neu.pmbackend.entity.Backlog;
import edu.neu.pmbackend.entity.Project;
import edu.neu.pmbackend.entity.Story;
import edu.neu.pmbackend.exception.ProjectIdException;
import edu.neu.pmbackend.exception.ProjectNotFoundException;
/**
 * @author gokuljayavel
 *
 */

@Service
public class StoryService {
	
	@Autowired
	private StoryDAOImpl storyDAO;
	
	@Autowired
	private ProjectService prservice;
	
	@Autowired
	private BacklogDAOImpl backlogDAO;
	
	
	
	
	public Story addStory(String projectIdentifier, Story story, String user) {
		
		Backlog backl = prservice.findProjectByIdentifier(projectIdentifier, user).getBacklog();
		
		 story.setBacklog(backl);
		 
		 Integer BacklogSequence = backl.getPTSequence();
		 
		 BacklogSequence++;
		 backl.setPTSequence(BacklogSequence);
		 
		 story.setProjectSequence(projectIdentifier+"-"+BacklogSequence);
		 story.setProjectIdentifier(projectIdentifier);
		
		 
		 if(story.getPriority()==null||story.getPriority()==0){
			 story.setPriority(3);
         }
         //INITIAL status when status is null
         if(story.getStatus()==null){
        	 story.setStatus(StoryStatus.TO_DO);
         }
		
		
		
		
		return storyDAO.save(story);
		
	}
	
	public Iterable<Story> getStoryById(String project_id, String user){
		
	try {
		
			Project pr = prservice.findProjectByIdentifier(project_id, user);

			if(pr==null){
	            throw new ProjectIdException("ProjectID " + project_id + " doesn't exists");
	        }
		return storyDAO.findByProjectIdentifierOrderByPriority(project_id);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
		return null;
		
		
		
	}
	
	public Story findStoryProjectSequence(String project_id, String story_id, String user) {
		
		prservice.findProjectByIdentifier(project_id, user);
		
		Story st = storyDAO.findByProjectSequence(story_id);
		
		if(st == null) {
			throw new ProjectNotFoundException("Story '"+story_id+"' not found");
		}
		
		if(!st.getProjectIdentifier().equals(project_id) ) {
			throw new ProjectNotFoundException("Story '"+story_id+"' does not exist in project: '"+project_id);
		}
		
		return st;

		
	}
	
	
	 public Story updateByProjectSequence(Story updatedTask, String project_id, String story_id, String user){
		 Story projectTask = findStoryProjectSequence(project_id, story_id,user);
		 
		 updatedTask.setBacklog(projectTask.getBacklog());
		 System.out.println(new Date());
		 updatedTask.setUpdate_At(new Date() );

		 projectTask = updatedTask;

	        return storyDAO.update(projectTask);
	    }
	 
	 
	 public void deleteStory(String project_id, String story_id, String user) {
		 
		 Story projectTask = findStoryProjectSequence(project_id, story_id, user);
		 
		 Backlog back = projectTask.getBacklog();
		  List<Story> stories = back.getProjectTasks();
		  stories.remove(projectTask);
		  backlogDAO.save(back);
		  storyDAO.delete(projectTask);
		  
		 
		 
		 
	 }

}
