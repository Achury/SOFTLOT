package com.lotrading.softlot.setup.entity;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:57 a.m.
 */
public class MasterValue implements Cloneable{

	private Log log = LogFactory.getLog(MasterValue.class);
	private int valueId;
	private int masterId;
	private java.lang.String value1;
	private java.lang.String value2;
	private java.lang.String value3;
	private java.util.Date enteredDate;
	private java.util.Date disabledDate;

	public MasterValue(){

	}
	public MasterValue(int id){
		this.valueId = id;
	}

	public int getValueId(){
		return valueId;
	}
	
	public MasterValue(int id, int masterId){
		this.masterId = masterId;
		this.valueId = id;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setValueId(int newVal){
		valueId = newVal;
	}

	public int getMasterId(){
		return masterId;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setMasterId(int newVal){
		masterId = newVal;
	}

	public java.lang.String getValue1(){
		return value1;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setValue1(java.lang.String newVal){
		value1 = newVal;
	}

	public java.lang.String getValue2(){
		return value2;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setValue2(java.lang.String newVal){
		value2 = newVal;
	}

	public java.lang.String getValue3(){
		return value3;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setValue3(java.lang.String newVal){
		value3 = newVal;
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
	
	public Object clone(){
        MasterValue obj=null;
        try{
            obj=(MasterValue)super.clone();
        }catch(CloneNotSupportedException ex){
        	log.error("no se puede duplicar el la entidad MasterValue. An Exception has been thrown " + ex);
        }
        return obj;
    }

}