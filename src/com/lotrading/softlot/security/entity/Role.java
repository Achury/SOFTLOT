package com.lotrading.softlot.security.entity;

import java.util.List;

/**
 * @author JOHNNATAN MERY
 * @version 1.0
 * @created 01-abr-2011 11:00:02 a.m.
 */
public class Role {

	private int roleId;
	private java.lang.String name;
	private java.lang.String description;
	private java.util.Date enteredDate;
	private java.util.Date disabledDate;
	private List resources;
	private List options;

	public Role(){

	}
	public Role(int roleId){
		this.roleId = roleId;
	}
	
	public Role(int roleId, String name){
		this.roleId = roleId;
		this.name = name;
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

	public java.lang.String getName(){
		return name;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setName(java.lang.String newVal){
		name = newVal;
	}

	public java.lang.String getDescription(){
		return description;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setDescription(java.lang.String newVal){
		description = newVal;
	}

	public java.util.Date getEnteredDate(){
		return enteredDate;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setEnteredDate(java.util.Date newVal){
		enteredDate = newVal;
	}

	public java.util.Date getDisabledDate(){
		return disabledDate;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setDisabledDate(java.util.Date newVal){
		disabledDate = newVal;
	}

	public List getResources(){
		return resources;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setResources(List newVal){
		resources = newVal;
	}
	public void setOptions(List options) {
		this.options = options;
	}
	public List getOptions() {
		return options;
	}

}