/**
 * 
 */
package edu.neu.pmbackend.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.neu.pmbackend.entity.Project;
import edu.neu.pmbackend.entity.Story;
import edu.neu.pmbackend.service.MapValidationErrorService;
import edu.neu.pmbackend.service.ProjectService;
import edu.neu.pmbackend.validator.ProjectValidator;

/**
 * @author gokuljayavel
 *
 */

@RestController
@RequestMapping(value = "/api/project")
@CrossOrigin(origins = " * ", allowedHeaders = " * ")
@Api(value = "Project Management project", tags = "Project Management project")
@Validated
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private ProjectValidator projectValidator;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	

	
	@GetMapping("/projectid/{id}")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "JWT Bearer token", dataType = "string", paramType = "header", required = true)
})
	public ResponseEntity<?> getProject(@PathVariable String id, Principal pr){
		
		Project proj = projectService.findProjectByIdentifier(id, pr.getName());
		
		return new ResponseEntity<Project>(proj, HttpStatus.OK);
		
		
		
	}
	
	 @DeleteMapping("/{id}")
	 @ApiImplicitParams({
	        @ApiImplicitParam(name = "Authorization", value = "JWT Bearer token", dataType = "string", paramType = "header", required = true)
	})
	    public ResponseEntity<?>  deleteProject(@PathVariable String id, Principal pr){
		 
		 projectService.deleteProjectByIdentifier(id, pr.getName());
		 
		 return new ResponseEntity<String>("Project with ID " + id + " deleted", HttpStatus.OK);
	 }
	 
	 
	 @GetMapping("/{user_id}")
	 
	 public Iterable<Project> getProject(@PathVariable Long user_id, Principal pr){
		 
		 return projectService.getAllProject(user_id);
		 
		 
	 }
	 
		@PostMapping("/{user_id}")
		@ApiImplicitParams({
	        @ApiImplicitParam(name = "Authorization", value = "JWT Bearer token", dataType = "string", paramType = "header", required = true)
	})
		public ResponseEntity<?> addProject(@Valid @RequestBody Project proj,
	            BindingResult result,
	            @PathVariable String user_id, Principal pr){
			
			projectValidator.validate(proj, result);
			
			ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationErrorService(result);
			
			if(errorMap!=null)
	            return errorMap;
			
			Project addedStory =projectService.saveorUpdate(proj, pr.getName());
			
			
			return new ResponseEntity<Project>(addedStory, HttpStatus.CREATED);
			
		}
	

}
