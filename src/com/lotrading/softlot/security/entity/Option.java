package com.lotrading.softlot.security.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JOHNNATAN MERY
 * @version 1.0
 * @created 01-abr-2011 10:59:58 a.m.
 */
public class Option {

	private int optionId;
	private java.lang.String name;
	private java.lang.String description;
	private Resource resource;
	private boolean parent;
	private int parentId;
	private int order;
	private java.util.Date enteredDate;
	private java.util.Date disabledDate;
	private List suboptions;

	public Option(){
		resource = new Resource();
		suboptions = new ArrayList();
	}

	public Option(int optionId){
		this.optionId = optionId;
	}


	public int getOptionId(){
		return optionId;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setOptionId(int newVal){
		optionId = newVal;
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

	public Resource getResource(){
		return resource;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setResource(Resource newVal){
		resource = newVal;
	}

	public boolean isParent(){
		return parent;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setParent(boolean newVal){
		parent = newVal;
	}

	public int getParentId(){
		return parentId;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setParentId(int newVal){
		parentId = newVal;
	}

	public int getOrder(){
		return order;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setOrder(int newVal){
		order = newVal;
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

	public void setSuboptions(List suboptions) {
		this.suboptions = suboptions;
	}

	public List getSuboptions() {
		return suboptions;
	}

}