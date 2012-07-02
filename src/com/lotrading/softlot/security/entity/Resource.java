package com.lotrading.softlot.security.entity;

import java.util.List;

/**
 * @author JOHNNATAN MERY
 * @version 1.0
 * @created 01-abr-2011 11:00:01 a.m.
 */
public class Resource {

	private int resourceId;
	private String name;
	private java.lang.String description;
	private java.lang.String url;
	private String type;
	private java.lang.String action;
	private java.util.Date enteredDate;
	private java.util.Date disabledDate;
	private List actions;
	private String stringActions;
	private int roleId;

	public Resource() {

	}

	public Resource(int resourceId) {
		this.resourceId = resourceId;
	}

	public int getResourceId() {
		return resourceId;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setResourceId(int newVal) {
		resourceId = newVal;
	}

	public java.lang.String getDescription() {
		return description;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setDescription(java.lang.String newVal) {
		description = newVal;
	}

	public java.lang.String getUrl() {
		return url;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setUrl(java.lang.String newVal) {
		url = newVal;
	}

	public String getType() {
		return type;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setType(String newVal) {
		type = newVal;
	}

	public java.lang.String getAction() {
		return action;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setAction(java.lang.String newVal) {
		action = newVal;
	}

	public java.util.Date getEnteredDate() {
		return enteredDate;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setEnteredDate(java.util.Date newVal) {
		enteredDate = newVal;
	}

	public java.util.Date getDisabledDate() {
		return disabledDate;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setDisabledDate(java.util.Date newVal) {
		disabledDate = newVal;
	}

	public List getActions() {
		return actions;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setActions(List newVal) {
		actions = newVal;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return this.getResourceId() + "-" + this.getName();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + resourceId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Resource other = (Resource) obj;
		if (resourceId != other.resourceId)
			return false;
		return true;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public void setStringActions(String stringActions) {
		this.stringActions = stringActions;
	}

	public String getStringActions() {
		return stringActions;
	}
}