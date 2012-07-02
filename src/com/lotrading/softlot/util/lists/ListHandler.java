package com.lotrading.softlot.util.lists;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:57 a.m.
 */
public abstract class ListHandler {

	private java.lang.String listId;
	protected java.util.List elements;
	public ListElement m_ListElement;

	public ListHandler(){

	}

	public void finalize() throws Throwable {

	}

	public java.util.List getElements(){
		return elements;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setElements(java.util.List newVal){
		elements = newVal;
	}

	public java.lang.String getListId(){
		return listId;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setListId(java.lang.String newVal){
		listId = newVal;
	}

	public boolean refreshList(){
		return false;
	}

	/**
	 * 
	 * @param listId
	 */
	public BaseElement getElement(java.lang.String listId){
		return null;
	}

	/**
	 * 
	 * @param element
	 */
	public void setElement(BaseElement element){

	}

	public void clearElements(){

	}

}