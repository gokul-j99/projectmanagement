/**
 * 
 */
package edu.neu.pmbackend.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author gokuljayavel
 *
 */
@Entity
public class Backlog {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column(name = "ptsequence")
    private Integer PTSequence = 0;
	@Column(name = "project_identifier")
    private String projectIdentifier;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="project",nullable = false)
    @JsonIgnore
    private Project project;

    
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "backlog", orphanRemoval = true)
    private List<Story> projectTasks = new ArrayList<>();

    
    public Backlog() {
    	
    }
    
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}




	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}




	/**
	 * @return the pTSequence
	 */
	public Integer getPTSequence() {
		return PTSequence;
	}




	/**
	 * @param pTSequence the pTSequence to set
	 */
	public void setPTSequence(Integer pTSequence) {
		PTSequence = pTSequence;
	}




	/**
	 * @return the projectIdentifier
	 */
	public String getProjectIdentifier() {
		return projectIdentifier;
	}




	/**
	 * @param projectIdentifier the projectIdentifier to set
	 */
	public void setProjectIdentifier(String projectIdentifier) {
		this.projectIdentifier = projectIdentifier;
	}




	/**
	 * @return the project
	 */
	public Project getProject() {
		return project;
	}




	/**
	 * @param project the project to set
	 */
	public void setProject(Project project) {
		this.project = project;
	}




	/**
	 * @return the projectTasks
	 */
	public List<Story> getProjectTasks() {
		return projectTasks;
	}




	/**
	 * @param projectTasks the projectTasks to set
	 */
	public void setProjectTasks(List<Story> projectTasks) {
		this.projectTasks = projectTasks;
	}




	@Override
	public String toString() {
		final int maxLen = 10;
		return "Backlog [id=" + id + ", PTSequence=" + PTSequence + ", projectIdentifier=" + projectIdentifier
				+ ", project=" + project + ", projectTasks="
				+ (projectTasks != null ? projectTasks.subList(0, Math.min(projectTasks.size(), maxLen)) : null) + "]";
	}
    
    
    

}
