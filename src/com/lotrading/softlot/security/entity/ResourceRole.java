package com.lotrading.softlot.security.entity;

/**
 * @author JOHNNATAN MERY
 * @version 1.0
 * @created 01-abr-2011 11:00:02 a.m.
 */
public class ResourceRole {

	private int roleId;
	private int resourceId;
	private Resource action;
	private int employeeId;

	public ResourceRole(){

	}

	public int getRoleId(){
		return roleId;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setRoleId(int newVal){
		roleId = newVal;
	}

	public int getResourceId(){
		return resourceId;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setResourceId(int newVal){
		resourceId = newVal;
	}


	public Resource getAction(){
		return action;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setAction(Resource newVal){
		action = newVal;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

}