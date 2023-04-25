/**
 * 
 */
package edu.neu.pmbackend.validator;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import edu.neu.pmbackend.dto.StoryStatus;
import edu.neu.pmbackend.entity.Story;

/**
 * @author gokuljayavel
 *
 */

@Component
public class StoryValidator implements Validator{

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
		
		
		Date endDate = story.getDueDate();
		//StoryStatus status = story.getStatus();
		
		//System.out.println(status);
		
//		if(!status.equals(StoryStatus.DONE)  && !status.equals(StoryStatus.TO_DO) && !status.equals(StoryStatus.IN_PROGRESS)) {
//			errors.rejectValue("status", "Null", "Status cannot be null and it should be like DONE orTO_DO or  IN_PROGRESS");
//		}
//		
		
		if( story.getStatus() == null) {
			errors.rejectValue("status", "Null", "Status cannot be empty and it should be like DONE orTO_DO or  IN_PROGRESS");
		}
		
		if(story.getStoryType().equals("")) {
			errors.rejectValue("status", "Null", "Story type cannot be empty and it should be like Developer or tester");
		}
		
		
		
		
	       if (endDate == null) {
	        	 errors.rejectValue("end_date", "Null", "Start date cannot be null");  
	    }
	       
	       
	       LocalDate localEndDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	       
	       
           if (localEndDate.isBefore(LocalDate.now())) {
          	 errors.rejectValue("dueDate", "date", "Due date cannot be before today");
          }
		
		
	}

}
