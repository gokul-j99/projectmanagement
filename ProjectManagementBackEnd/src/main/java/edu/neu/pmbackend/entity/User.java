/**
 * 
 */
package edu.neu.pmbackend.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author gokuljayavel
 *
 */

@Entity
public class User {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Email(message = "Username needs to be an email")
	    @NotBlank(message = "username is required")
	    @Column(unique = true)
	    private String username;
	    @NotBlank(message = "Please enter your full name")
	    private String fullName;
	    @NotBlank(message = "Password field is required")
	    private String password;
	    @Transient
	    private String confirmPassword;
	    private Date create_At;
	    private Date update_At;
	    private String Role;
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
		 * @return the username
		 */
		public String getUsername() {
			return username;
		}
		/**
		 * @param username the username to set
		 */
		public void setUsername(String username) {
			this.username = username;
		}
		/**
		 * @return the fullName
		 */
		public String getFullName() {
			return fullName;
		}
		/**
		 * @param fullName the fullName to set
		 */
		public void setFullName(String fullName) {
			this.fullName = fullName;
		}
		/**
		 * @return the password
		 */
		public String getPassword() {
			return password;
		}
		/**
		 * @param password the password to set
		 */
		public void setPassword(String password) {
			this.password = password;
		}
		/**
		 * @return the confirmPassword
		 */
		public String getConfirmPassword() {
			return confirmPassword;
		}
		/**
		 * @param confirmPassword the confirmPassword to set
		 */
		public void setConfirmPassword(String confirmPassword) {
			this.confirmPassword = confirmPassword;
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
		/**
		 * @return the role
		 */
		public String getRole() {
			return Role;
		}

		/**
		 * @param role the role to set
		 */
		public void setRole(String role) {
			Role = role;
		}
		@PrePersist
		 protected void onCreate(){
		        this.create_At = new Date();
		    }

		 @PreUpdate
		  protected void onUpdate(){
		        this.update_At = new Date();
		    }
		

	    
	    

}
