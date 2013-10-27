package com.asu.ss.secure_banking_system.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="REQUEST")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="discriminator",
discriminatorType = DiscriminatorType.STRING
)

@DiscriminatorValue("RE")
public class RequestEntity {

	@Id
	@Column(name = "request_id")
	private int requestID;
	
	
	
	@Column(name = "request_time")
	private Date requestTime;

	public int getRequestID() {
		return requestID;
	}

	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}

	public Date getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}
	
	@ManyToOne
	@JoinColumn(name="requested_by")
	private UserEntity requestedBy;
	

	public UserEntity getRequestedBy() {
		return requestedBy;
	}

	public void setRequestedBy(UserEntity requestedBy) {
		this.requestedBy = requestedBy;
	}
}