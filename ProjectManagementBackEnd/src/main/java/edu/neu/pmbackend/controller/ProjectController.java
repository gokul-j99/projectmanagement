/**
 * 
 */
package edu.neu.pmbackend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.neu.pmbackend.entity.Project;
import edu.neu.pmbackend.service.MapValidationErrorService;
import edu.neu.pmbackend.service.ProjectService;

/**
 * @author gokuljayavel
 *
 */
//@CrossOrigin
@RestController
@RequestMapping(value = "/api/project")
//@Validated
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	@PostMapping("/")
	public ResponseEntity<?> createNewProject(@Valid @RequestBody Project proj,BindingResult result){
		
		ResponseEntity<?> errorMAp = mapValidationErrorService.MapValidationErrorService(result);
		
		if (errorMAp != null) {
			return errorMAp;
		}
		
		System.out.println("inside controller");
		projectService.saveorUpdate(proj);
		
		return new ResponseEntity<Project>(proj, HttpStatus.CREATED);
		
		
	}
	

}
