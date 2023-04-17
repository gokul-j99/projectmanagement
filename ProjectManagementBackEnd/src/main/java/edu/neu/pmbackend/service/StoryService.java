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
	
	
	
	
	public Story addStory(String projectIdentifier, Story story) {
		
		Backlog backl = prservice.findProjectByIdentifier(projectIdentifier).getBacklog();
		
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
	
	public Iterable<Story> getStoryById(String project_id){
		
	try {
		
			Project pr = prservice.findProjectByIdentifier(project_id);

			if(pr==null){
	            throw new ProjectIdException("ProjectID " + project_id + " doesn't exists");
	        }
		return storyDAO.findByProjectIdentifierOrderByPriority(project_id);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
		return null;
		
		
		
	}
	
	public Story findStoryProjectSequence(String project_id, String story_id) {
		
		prservice.findProjectByIdentifier(project_id);
		
		Story st = storyDAO.findByProjectSequence(story_id);
		
		return st;

		
	}
	
	
	 public Story updateByProjectSequence(Story updatedTask, String project_id, String story_id){
		 Story projectTask = findStoryProjectSequence(project_id, story_id);
		 
		 updatedTask.setBacklog(projectTask.getBacklog());
		 System.out.println(new Date());
		 updatedTask.setUpdate_At(new Date() );

		 projectTask = updatedTask;

	        return storyDAO.update(projectTask);
	    }
	 
	 
	 public void deleteStory(String project_id, String story_id) {
		 
		 Story projectTask = findStoryProjectSequence(project_id, story_id);
		 
		 Backlog back = projectTask.getBacklog();
		  List<Story> stories = back.getProjectTasks();
		  stories.remove(projectTask);
		  backlogDAO.save(back);
		  storyDAO.delete(projectTask);
		  
		 
		 
		 
	 }

}
