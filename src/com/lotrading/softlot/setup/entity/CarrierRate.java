package com.lotrading.softlot.setup.entity;

import java.util.Date;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 27-Sep-2011 12:00:00 AM
 */
public class CarrierRate {

	private int carrierRateId;
	private int carrierPortId;
	private MasterValue chargeType;
	private MasterValue rateType;
	private double rate;
	private double minimum;
	private boolean otherCharge;
	private boolean flat;
	private Date createdDate;

	private boolean showInMaster;
	
	public CarrierRate(){
		chargeType = new MasterValue();
		rateType = new MasterValue();
	}
	
	public int getCarrierRateId(){
		return carrierRateId;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setCarrierRateId(int newVal){
		carrierRateId = newVal;
	}

	public double getRate(){
		return rate;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setRate(double newVal){
		rate = newVal;
	}

	public double getMinimum(){
		return minimum;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setMinimum(double newVal){
		minimum = newVal;
	}

	public boolean isOtherCharge(){
		return otherCharge;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setOtherCharge(boolean newVal){
		otherCharge = newVal;
	}

	public Date getCreatedDate(){
		return createdDate;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setCreatedDate(Date newVal){
		createdDate = newVal;
	}

	public int getCarrierPortId() {
		return carrierPortId;
	}

	public void setCarrierPortId(int carrierPortId) {
		this.carrierPortId = carrierPortId;
	}

	public MasterValue getChargeType() {
		return chargeType;
	}

	public void setChargeType(MasterValue chargeType) {
		this.chargeType = chargeType;
	}

	public MasterValue getRateType() {
		return rateType;
	}

	public void setRateType(MasterValue rateType) {
		this.rateType = rateType;
	}

	public boolean isFlat() {
		return flat;
	}

	public void setFlat(boolean flat) {
		this.flat = flat;
	}

	public boolean isShowInMaster() {
		return showInMaster;
	}

	public void setShowInMaster(boolean showInMaster) {
		this.showInMaster = showInMaster;
	}

}