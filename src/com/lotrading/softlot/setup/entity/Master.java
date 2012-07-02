package com.lotrading.softlot.setup.entity;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:57 a.m.
 */
public class Master {

	private int masterId;
	private java.lang.String name;
	private java.lang.String description;
	private java.util.List masterValues;
	private String format1;
	private String format2;
	private String format3;
	private java.util.Date enteredDate;
	private java.util.Date disabledDate;

	public Master() {

	}

	public void finalize() throws Throwable {

	}

	public int getMasterId() {
		return masterId;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setMasterId(int newVal) {
		masterId = newVal;
	}

	public java.lang.String getName() {
		return name;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setName(java.lang.String newVal) {
		name = newVal;
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

	public java.util.List getMasterValues() {
		return masterValues;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setMasterValues(java.util.List newVal) {
		masterValues = newVal;
	}

	public String getFormat1() {
		return format1;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setFormat1(String newVal) {
		format1 = newVal;
	}

	public String getFormat2() {
		return format2;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setFormat2(String newVal) {
		format2 = newVal;
	}

	public String getFormat3() {
		return format3;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setFormat3(String newVal) {
		format3 = newVal;
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

}