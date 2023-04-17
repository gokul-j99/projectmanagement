/**
 * 
 */
package edu.neu.pmbackend.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author gokuljayavel
 *
 */

@Entity
public class User implements UserDetails{
	
	 	private static final long serialVersionUID = 1L;

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
	    //@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$", message = "Password must contain at least 8 characters, one letter, one number, and one special character")
	    private String password;
	    @Transient
	    private String confirmPassword;
	    private Date create_At;
	    private Date update_At;
	    @Column(name = "role", columnDefinition = "varchar(25) default 'Manager'")
	    private String role;
	    
	    
	    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "user", orphanRemoval = true)
	    private List<Project> projects = new ArrayList<>();
	    
	    
	    
	    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "manager", orphanRemoval = true)
	    private List<TeamMember> teammembers = new ArrayList<>();
	    
	    
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
			return role;
		}

		/**
		 * @param role the role to set
		 */
		public void setRole(String role) {
			this.role = role;
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
		@JsonIgnore
		public Collection<? extends GrantedAuthority> getAuthorities() {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		@JsonIgnore
		public boolean isAccountNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}
		@Override
		@JsonIgnore
		public boolean isAccountNonLocked() {
			// TODO Auto-generated method stub
			return true;
		}
		@Override
		public String toString() {
			return "User [id=" + id + ", username=" + username + ", fullName=" + fullName + ", password=" + password
					+ ", confirmPassword=" + confirmPassword + ", create_At=" + create_At + ", update_At=" + update_At
					+ ", role=" + role + "]";
		}
		@Override
		@JsonIgnore
		public boolean isCredentialsNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}
		@Override
		@JsonIgnore
		public boolean isEnabled() {
			// TODO Auto-generated method stub
			return true;
		}
		

	    
	    

}
