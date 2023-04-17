/**
 * 
 */
package edu.neu.pmbackend.exception;

/**
 * @author gokuljayavel
 *
 */
public class ProjectNotfoundExceptionResponse {
	
	
private String projectNotfound;
	
	public ProjectNotfoundExceptionResponse (String projectNotfound ) {
		this.projectNotfound = projectNotfound;
	}

	public String getProjectIdentifier() {
		return projectNotfound;
	}

	public void setProjectIdentifier(String projectNotfound) {
		this.projectNotfound = projectNotfound;
	}

}
