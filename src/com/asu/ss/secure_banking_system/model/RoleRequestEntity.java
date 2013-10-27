package com.asu.ss.secure_banking_system.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("RRE")
public class RoleRequestEntity extends RequestEntity{	
	
	@ManyToOne
	@JoinColumn(name="role_id")
	private RoleEntity role;
	
	public RoleEntity getRole() {
		return role;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}
	
	@ManyToOne
	@JoinColumn(name="request_for_user")
	private UserEntity requestForUser;
	public UserEntity getRequestForUser() {
		return requestForUser;
	}

	public void setUser(UserEntity requestForUser) {
		this.requestForUser = requestForUser;
	}


	@Column(name="is_validated")
	private boolean isValidated;
	public boolean isValidated() {
		return isValidated;
	}

	public void setValidated(boolean isValidated) {
		this.isValidated = isValidated;
	}

	@Column(name="is_assigned")
	private boolean isAssigned;
	public boolean isAssigned() {
		return isAssigned;
	}

	public void setAssigned(boolean isAssigned) {
		this.isAssigned = isAssigned;
	}	

	
}
