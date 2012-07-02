package com.lotrading.softlot.businessPartners.entity;
import java.util.List;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 11:00:00 a.m.
 */
public class PartnerDepartmentInfo {

	private int partnerDepartmentInfoId;
	private int partnerId;
	private int departmentLotId;
	private int employeeRep;
	private java.lang.String notes;
	private float margin;
	private float markup;
	private List<PartnerContact> partnerContacts;
	private List<CourierAccount> courierAccounts;

	public PartnerDepartmentInfo(){

	}

	
	public int getPartnerDepartmentInfoId(){
		return partnerDepartmentInfoId;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setPartnerDepartmentInfoId(int newVal){
		partnerDepartmentInfoId = newVal;
	}

	public int getPartnerId(){
		return partnerId;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setPartnerId(int newVal){
		partnerId = newVal;
	}

	public int getDepartmentLotId(){
		return departmentLotId;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setDepartmentLotId(int newVal){
		departmentLotId = newVal;
	}

	public int getEmployeeRep(){
		return employeeRep;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setEmployeeRep(int newVal){
		employeeRep = newVal;
	}

	public java.lang.String getNotes(){
		return notes;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setNotes(java.lang.String newVal){
		notes = newVal;
	}

	public float getMargin(){
		return margin;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setMargin(float newVal){
		margin = newVal;
	}

	public float getMarkup(){
		return markup;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setMarkup(float newVal){
		markup = newVal;
	}

	public List<PartnerContact> getPartnerContacts(){
		return partnerContacts;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setPartnerContacts(List<PartnerContact> newVal){
		partnerContacts = newVal;
	}

	public List<CourierAccount> getCourierAccounts(){
		return courierAccounts;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setCourierAccounts(List<CourierAccount> newVal){
		courierAccounts = newVal;
	}


}