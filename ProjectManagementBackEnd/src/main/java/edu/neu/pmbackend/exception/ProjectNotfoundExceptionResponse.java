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

	/**
	 * @return the projectNotfound
	 */
	public String getProjectNotfound() {
		return projectNotfound;
	}

	/**
	 * @param projectNotfound the projectNotfound to set
	 */
	public void setProjectNotfound(String projectNotfound) {
		this.projectNotfound = projectNotfound;
	}



}
