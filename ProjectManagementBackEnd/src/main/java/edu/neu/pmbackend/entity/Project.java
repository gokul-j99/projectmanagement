/**
 * 
 */
package edu.neu.pmbackend.entity;

import java.util.Date;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;



/**
 * @author gokuljayavel
 *
 */

@Entity
@Table(name = "project")
public class Project {
	
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "project_name")
    private String projectName;
    @Column(name = "project_identifier",updatable = false,unique = true)
    private String projectIdentifier;
    @Column(name = "description")
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date start_date;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date end_date;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(updatable = false)
    private Date created_At;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updated_At;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "project")
    @JsonIgnore
    private Backlog backlog;

    @Column(name = "project_leader")
    private String projectLeader;
    
	@ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

 
	
	public Project() {
		
	}

	
	
	/**
	 * @return the backlog
	 */
	public Backlog getBacklog() {
		return backlog;
	}

	/**
	 * @param backlog the backlog to set
	 */
	public void setBacklog(Backlog backlog) {
		this.backlog = backlog;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}


	 public String getDescription() {
	        return description;
	    }

	  public void setDescription(String description) {
	        this.description = description;
	    }


	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getProjectName() {
		return projectName;
	}



	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}



	public String getProjectIdentifier() {
		return projectIdentifier;
	}



	public void setProjectIdentifier(String projectIdentifier) {
		this.projectIdentifier = projectIdentifier;
	}




	public Date getStart_date() {
		return start_date;
	}



	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}



	public Date getEnd_date() {
		return end_date;
	}



	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}



	public Date getCreated_At() {
		return created_At;
	}



	public void setCreated_At(Date created_At) {
		this.created_At = created_At;
	}



	public Date getUpdated_At() {
		return updated_At;
	}



	public void setUpdated_At(Date updated_At) {
		this.updated_At = updated_At;
	}
	
	   /**
		 * @return the projectLeader
		 */
		public String getProjectLeader() {
			return projectLeader;
		}

		/**
		 * @param projectLeader the projectLeader to set
		 */
		public void setProjectLeader(String projectLeader) {
			this.projectLeader = projectLeader;
		}



	@PrePersist
	protected void onCreate() {
		this.created_At = new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updated_At = new Date();
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", projectName=" + projectName + ", projectIdentifier=" + projectIdentifier
				+ ", description=" + description + ", start_date=" + start_date + ", end_date=" + end_date
				+ ", created_At=" + created_At + ", updated_At=" + updated_At 
				+ ", projectLeader=" + projectLeader + ", user=" + user + "]";
	}

}
