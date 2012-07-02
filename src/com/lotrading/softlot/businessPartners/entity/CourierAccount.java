package com.lotrading.softlot.businessPartners.entity;

import com.lotrading.softlot.setup.entity.MasterValue;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:52 a.m.
 */
public class CourierAccount {

	private int partnerDeptInfo;
	private int courierAccountId;
	private MasterValue courier;
	private String accountNumber;
	private boolean main;

	public CourierAccount(){
		courier = new MasterValue();
	}

	public int getCourierAccountId(){
		return courierAccountId;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setCourierAccountId(int newVal){
		courierAccountId = newVal;
	}

	public String getAccountNumber(){
		return accountNumber;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setAccountNumber(String newVal){
		accountNumber = newVal;
	}

	public boolean isMain(){
		return main;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setMain(boolean newVal){
		main = newVal;
	}

	public void setPartnerDeptInfo(int partnerDeptInfo) {
		this.partnerDeptInfo = partnerDeptInfo;
	}

	public int getPartnerDeptInfo() {
		return partnerDeptInfo;
	}

	public void setCourier(MasterValue courier) {
		this.courier = courier;
	}

	public MasterValue getCourier() {
		return courier;
	}

}