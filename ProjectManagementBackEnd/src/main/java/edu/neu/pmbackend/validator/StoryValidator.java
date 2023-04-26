/**
 * 
 */
package edu.neu.pmbackend.validator;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import edu.neu.pmbackend.dao.ProjectDAOImpl;
import edu.neu.pmbackend.dto.StoryStatus;
import edu.neu.pmbackend.entity.Story;

/**
 * @author gokuljayavel
 *
 */

@Component
public class StoryValidator implements Validator{
	
	
	@Autowired
	private ProjectDAOImpl project;

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	//@SuppressWarnings("unlikely-arg-type")
	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		
		Story story = (Story) target;
		
		System.out.println(story);
		
		
		
		Date endDate = story.getDueDate();

		
		
		
		System.out.println(story.getProjectIdentifier());
		
		
		System.out.println(project.findByProjectIdentifier(story.getProjectIdentifier()));
		
		Date due_date = project.findByProjectIdentifier(story.getProjectIdentifier()).getEnd_date();
		
		
		System.out.println(due_date);
		
		
		if(story.getPriority() == 0 ) {
			errors.rejectValue("priority", "Null", "priority cannot be empty and it should be like Low High or  Medium");
		}
		
	
		if( story.getStatus() == null) {
			errors.rejectValue("status", "Null", "Status cannot be empty and it should be like DONE orTO_DO or  IN_PROGRESS");
		}
		
		if(story.getStoryType().equals("")) {
			errors.rejectValue("storyType", "Null", "Story type cannot be empty and it should be like Developer or tester");
		}
		
		if(story.getAcceptanceCriteria().equals("")) {
			errors.rejectValue("acceptanceCriteria", "Null", "acceptanceCriteria cannot be empty");
		}
		
		if(story.getSummary().equals("")) {
			errors.rejectValue("summary", "Null", "summary cannot be empty");
		}
		
		
		
		
		
	      if (endDate == null) {
	        	 errors.rejectValue("dueDate", "Null", "End date cannot be null");  
	    }
	      else {
	    	  
	    	  LocalDate localEndDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		       
		       LocalDate project_EndDate = due_date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		       System.out.println(project_EndDate);
		       
	           if (localEndDate.isBefore(LocalDate.now())) {
	          	 errors.rejectValue("dueDate", "date", "Due date cannot be before today");
	          }
	           
	           if (project_EndDate.isBefore(localEndDate)) {
	            	 errors.rejectValue("dueDate", "date", "Due date cannot be after Project end date");
	            }
	    	  
	      }
	       
	       
	       
		
		
	}

}
