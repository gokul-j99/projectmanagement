/**
 * 
 */
package edu.neu.pmbackend.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author gokuljayavel
 *
 */
@Entity
public class Story {
	
	
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    @Column(updatable = false, unique = true)
	    private String projectSequence;
	    @NotBlank(message = "Please include a project summary")
	    private String summary;
	    private String acceptanceCriteria;
	    private String status;
	    private Integer priority;
	    @JsonFormat(pattern = "yyyy-mm-dd")
	    private Date dueDate;

	    //ManyToOne with Backlog
	    @ManyToOne(fetch =FetchType.EAGER)
	    @JoinColumn(name="backlog_id",updatable = false,nullable = false)
	    @JsonIgnore
	    private Backlog backlog;

	    @Column(updatable = false)
	    private String projectIdentifier;
	    @JsonFormat(pattern = "yyyy-mm-dd")
	    private Date create_At;
	    @JsonFormat(pattern = "yyyy-mm-dd")
	    private Date update_At;
	    
	    
	    public Story() {
	    	
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
		 * @return the projectSequence
		 */
		public String getProjectSequence() {
			return projectSequence;
		}
		/**
		 * @param projectSequence the projectSequence to set
		 */
		public void setProjectSequence(String projectSequence) {
			this.projectSequence = projectSequence;
		}
		/**
		 * @return the summary
		 */
		public String getSummary() {
			return summary;
		}
		/**
		 * @param summary the summary to set
		 */
		public void setSummary(String summary) {
			this.summary = summary;
		}
		/**
		 * @return the acceptanceCriteria
		 */
		public String getAcceptanceCriteria() {
			return acceptanceCriteria;
		}
		/**
		 * @param acceptanceCriteria the acceptanceCriteria to set
		 */
		public void setAcceptanceCriteria(String acceptanceCriteria) {
			this.acceptanceCriteria = acceptanceCriteria;
		}
		/**
		 * @return the status
		 */
		public String getStatus() {
			return status;
		}
		/**
		 * @param status the status to set
		 */
		public void setStatus(String status) {
			this.status = status;
		}
		/**
		 * @return the priority
		 */
		public Integer getPriority() {
			return priority;
		}
		/**
		 * @param priority the priority to set
		 */
		public void setPriority(Integer priority) {
			this.priority = priority;
		}
		/**
		 * @return the dueDate
		 */
		public Date getDueDate() {
			return dueDate;
		}
		/**
		 * @param dueDate the dueDate to set
		 */
		public void setDueDate(Date dueDate) {
			this.dueDate = dueDate;
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
		 * @return the create_At
		 */
		public Date getCreate_At() {
			return create_At;
		}
		/**
		 * @param create_At the create_At to set
		 */
		public void setCreate_At(Date create_At) {
			this.create_At = create_At;
		}
		/**
		 * @return the update_At
		 */
		public Date getUpdate_At() {
			return update_At;
		}
		/**
		 * @param update_At the update_At to set
		 */
		public void setUpdate_At(Date update_At) {
			this.update_At = update_At;
		}
		
		 @PrePersist
		    protected void onCreate(){
		        this.create_At = new Date();
		    }

		    @PreUpdate
		    protected void onUpdate(){
		        this.update_At = new Date();
		    }
		@Override
		public String toString() {
			return "Story [id=" + id + ", projectSequence=" + projectSequence + ", summary=" + summary
					+ ", acceptanceCriteria=" + acceptanceCriteria + ", status=" + status + ", priority=" + priority
					+ ", dueDate=" + dueDate + ", backlog=" + backlog + ", projectIdentifier=" + projectIdentifier
					+ ", create_At=" + create_At + ", update_At=" + update_At + "]";
		}
	    
	    
	    

}