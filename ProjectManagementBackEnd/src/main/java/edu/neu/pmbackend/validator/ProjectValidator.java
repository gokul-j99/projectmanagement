/**
 * 
 */
package edu.neu.pmbackend.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import edu.neu.pmbackend.entity.Project;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


/**
 * @author gokuljayavel
 *
 */
@Component
public class ProjectValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressWarnings("unused")
	@Override
	public void validate(Object target, Errors errors) {
		
		Project project = (Project) target;
		
		System.out.println("Inside project validation");
		
		Date startDate = project.getStart_date();
        Date endDate = project.getEnd_date();
        
        System.out.println(startDate);
        System.out.println(endDate);
        
        
        String projectName = project.getProjectName();
        String projectId = project.getProjectIdentifier();
        String projetDescription = project.getDescription();
        
        if(projectName.length() > 255) {
        	 errors.rejectValue("projectName", "projectName.length", "Project Name can't be greater than 255 char");
        }
        
        if(projectId.length() > 5) {
       	 errors.rejectValue("projectName", "projectIdentifier.length", "Project Name can't be greater than 5 length");
       }
        
        if(projetDescription.length() > 255 ) {
        	 errors.rejectValue("description", "description.length", "Project Name can't be greater than 255 char");
        }
        

        if (startDate == null) {
        	 errors.rejectValue("start_date", "Null", "Start date cannot be null");
           
        }

        if (endDate == null) {
        	 errors.rejectValue("end_date", "Null", "Start date cannot be null");  
    }
        
        
        

        if (startDate != null && endDate != null) {
            LocalDate localStartDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate localEndDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            
            if (localStartDate.isAfter(localEndDate)) {
            	System.out.println("Inside error date");
            	
            
            	 errors.rejectValue("start_date", "date", "Start date cannot be befor end date");
            }
            if (localStartDate.isBefore(LocalDate.now())) {
            	 errors.rejectValue("start_date", "date", "Start date cannot be before today");
            }
        }
		
	}
	
	
	  public static boolean isDateNotLessThanToday(Date date) {
		    LocalDate givenDate = new java.sql.Date(date.getTime()).toLocalDate();
		    LocalDate today = LocalDate.now();
		    return !givenDate.isBefore(today);
		  }

}
