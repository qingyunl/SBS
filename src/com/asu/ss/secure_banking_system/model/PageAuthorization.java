package com.asu.ss.secure_banking_system.model;

public class PageAuthorization {

	/**
	 * 
	 */
	private boolean isAssignRoleAccessible;
	public boolean isAssignTAAAccessible() {
		return isAssignTAAAccessible;
	}

	public void setAssignTAAAccessible(boolean isAssignTAAAccessible) {
		this.isAssignTAAAccessible = isAssignTAAAccessible;
	}

	public boolean isAdminNotificationAccessible() {
		return isAdminNotificationAccessible;
	}

	public void setAdminNotificationAccessible(boolean isAdminNotificationAccessible) {
		this.isAdminNotificationAccessible = isAdminNotificationAccessible;
	}

	public boolean isRequestRoleAccessible() {
		return isRequestRoleAccessible;
	}

	public void setRequestRoleAccessible(boolean isRequestRoleAccessible) {
		this.isRequestRoleAccessible = isRequestRoleAccessible;
	}

	public boolean isRequestTAAAccessible() {
		return isRequestTAAAccessible;
	}

	public void setRequestTAAAccessible(boolean isRequestTAAAccessible) {
		this.isRequestTAAAccessible = isRequestTAAAccessible;
	}

	public boolean isTAANotificationAccessible() {
		return isTAANotificationAccessible;
	}

	public void setTAANotificationAccessible(boolean isTAANotificationAccessible) {
		this.isTAANotificationAccessible = isTAANotificationAccessible;
	}

	public boolean isProcessTAAAccessible() {
		return processTAAAccessible;
	}

	public void setProcessTAAAccessible(boolean processTAAAccessible) {
		this.processTAAAccessible = processTAAAccessible;
	}

	private boolean isAssignTAAAccessible;
	private boolean isAdminNotificationAccessible;
	private boolean isRequestRoleAccessible;
	private boolean isRequestTAAAccessible;
	private boolean isTAANotificationAccessible;
	private boolean processTAAAccessible;
	
	public boolean isAssignRoleAccessible() {
		return isAssignRoleAccessible;
	}

	public void setAssignRoleAccessible(boolean isAssignRoleAccessible) {
		this.isAssignRoleAccessible = isAssignRoleAccessible;
	}
}
