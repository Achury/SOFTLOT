package com.lotrading.softlot.businessPartners.entity;

import java.util.Date;
import com.lotrading.softlot.setup.entity.MasterValue;



/**
 * @author MARLON PINEDA
 * @version 1.0
 * @created 01-abr-2011 11:00:01 a.m.
 */
public class Phone {

	private int phoneId;
	private java.lang.String countryCode;
	private java.lang.String areaCode;
	private java.lang.String phoneNumber;
	private java.lang.String phoneExtension;
	private MasterValue type;
	private boolean mainPhone;
	private Date enteredDate;
	private Date disabledDate;

	public Phone(){
		type = new MasterValue();
	}
	public Phone(int phoneId){
		this.phoneId = phoneId;
	}

	public int getPhoneId(){
		return phoneId;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setPhoneId(int newVal){
		phoneId = newVal;
	}

	public java.lang.String getPhoneNumber(){
		return phoneNumber;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setPhoneNumber(java.lang.String newVal){
		phoneNumber = newVal;
	}

	public java.lang.String getPhoneExtension(){
		return phoneExtension;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setPhoneExtension(java.lang.String newVal){
		phoneExtension = newVal;
	}

	public java.lang.String getAreaCode(){
		return areaCode;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setAreaCode(java.lang.String newVal){
		areaCode = newVal;
	}

	public java.lang.String getCountryCode(){
		return countryCode;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setCountryCode(java.lang.String newVal){
		countryCode = newVal;
	}

	public MasterValue getType() {
		return type;
	}

	public void setType(MasterValue type) {
		this.type = type;
	}

	public Date getEnteredDate() {
		return enteredDate;
	}

	public void setEnteredDate(Date enteredDate) {
		this.enteredDate = enteredDate;
	}

	public Date getDisabledDate() {
		return disabledDate;
	}

	public void setDisabledDate(Date disabledDate) {
		this.disabledDate = disabledDate;
	}
	
	public boolean isEmpty(Phone phone){
		if(phone.getAreaCode() == null || phone.getAreaCode().isEmpty()){
			if(phone.getCountryCode() == null || phone.getCountryCode().isEmpty()){
				if(phone.getPhoneExtension() == null || phone.getPhoneExtension().isEmpty()){
					if(phone.getPhoneNumber() == null || phone.getPhoneNumber().isEmpty()){
						return true;
					}
				}
			}
			return false;
		}else{
			return false;
		}
	}
	public boolean isMainPhone() {
		return mainPhone;
	}
	public void setMainPhone(boolean mainPhone) {
		this.mainPhone = mainPhone;
	}

}