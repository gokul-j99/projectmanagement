/**
 * 
 */
package edu.neu.pmbackend.exception;

/**
 * @author gokuljayavel
 *
 */
public class ProjectDeleteExceptionResponse {
	
	
	private String storylist;
	
	public ProjectDeleteExceptionResponse (String storylist ) {
		this.storylist = storylist;
	}

	/**
	 * @return the storylist
	 */
	public String getStorylist() {
		return storylist;
	}

	/**
	 * @param storylist the storylist to set
	 */
	public void setStorylist(String storylist) {
		this.storylist = storylist;
	}


}
