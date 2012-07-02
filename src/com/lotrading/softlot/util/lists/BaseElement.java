package com.lotrading.softlot.util.lists;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:50 a.m.
 */
public class BaseElement implements ListElement {

	private int id;
	private int parentId;
	private java.lang.String name;

	public BaseElement(){

	}
	
	public BaseElement(int id, int parentId, String name){
		this.id = id;
		this.parentId = parentId;
		this.name = name;
	}

	public void finalize() throws Throwable {

	}

	public int getId(){
		return id;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setId(int newVal){
		id = newVal;
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

}