/**
 * 
 */
package edu.neu.pmbackend.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import edu.neu.pmbackend.entity.Story;
import edu.neu.pmbackend.service.MapValidationErrorService;
import edu.neu.pmbackend.service.StoryService;
import edu.neu.pmbackend.validator.StoryValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

/**
 * @author gokuljayavel
 *
 */


@RestController
@RequestMapping("/api/backlog")
@CrossOrigin(origins = " * ", allowedHeaders = " * ")
@Api(value = "Project Management Stories", tags = "Project Management Stories ")
public class StroyController {
	
	
	@Autowired
	private StoryService storyService;
	
	@Autowired
	private StoryValidator storyValidator;
	
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	@PostMapping("/{project_id}")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "JWT Bearer token", dataType = "string", paramType = "header", required = true)
})
	public ResponseEntity<?> addStory(@Valid @RequestBody Story story,
            BindingResult result,
            @PathVariable String project_id, Principal pr){
		
		storyValidator.validate(story, result);
		
		ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationErrorService(result);
		
		if(errorMap!=null)
            return errorMap;
		
		Story addedStory = storyService.addStory(project_id, story, pr.getName());
		
		
		return new ResponseEntity<Story>(addedStory, HttpStatus.CREATED);
		
	}
	
	
	
	 @GetMapping("/{project_id}")
	 @ApiImplicitParams({
	        @ApiImplicitParam(name = "Authorization", value = "JWT Bearer token", dataType = "string", paramType = "header", required = true)
	})
	    public Iterable<Story> getListOfStory(@PathVariable String project_id, Principal pr){
		 
		  return storyService.getStoryById(project_id, pr.getName());
	    }
	 
	 
	  @GetMapping("/{project_id}/{story_id}")
	  @ApiImplicitParams({
	        @ApiImplicitParam(name = "Authorization", value = "JWT Bearer token", dataType = "string", paramType = "header", required = true)
	})
	    public ResponseEntity<?> getProjectStoryById(@PathVariable String project_id, @PathVariable String story_id, Principal pr){
		  Story story = storyService.findStoryProjectSequence(project_id, story_id, pr.getName());
	        return new ResponseEntity<Story>( story, HttpStatus.OK);
	    }
	  
	  
	  @PatchMapping("/{project_id}/{story_id}")
	  @ApiImplicitParams({
	        @ApiImplicitParam(name = "Authorization", value = "JWT Bearer token", dataType = "string", paramType = "header", required = true)
	})
	    public ResponseEntity<?> updateProjectStory(@Valid @RequestBody Story projectTask, BindingResult result,
	                                               @PathVariable String project_id, @PathVariable String story_id,
	                                               Principal pr){
		  
		  
		  storyValidator.validate(projectTask, result);

	        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationErrorService(result);
	        if (errorMap != null) return errorMap;

	        Story updatedTask = storyService.updateByProjectSequence(projectTask,project_id,story_id, pr.getName());

	        return new ResponseEntity<Story>(updatedTask,HttpStatus.OK);

	    }
	  
	  
	    @DeleteMapping("/{project_id}/{story_id}")
	    @ApiImplicitParams({
	        @ApiImplicitParam(name = "Authorization", value = "JWT Bearer token", dataType = "string", paramType = "header", required = true)
	})
	    public ResponseEntity<?> deleteProjectStory(@PathVariable String project_id, @PathVariable String story_id, Principal pr){
	        storyService.deleteStory(project_id, story_id, pr.getName());

	        return new ResponseEntity<String>("Story "+story_id+" was deleted successfully", HttpStatus.OK);
	    }
	    
	    
		 @GetMapping("/dev/{project_id}")
		 @ApiImplicitParams({
		        @ApiImplicitParam(name = "Authorization", value = "JWT Bearer token", dataType = "string", paramType = "header", required = true)
		})
		    public Iterable<Story> getListOfDevStory(@PathVariable String project_id, Principal pr){
			 
			  return storyService.getDevStoryById(project_id, pr.getName());
			  
		 }
		    
	 
	

}
