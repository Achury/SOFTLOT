package com.lotrading.softlot.businessPartners.entity;

import java.util.Date;

import com.lotrading.softlot.setup.entity.MasterValue;
import com.lotrading.softlot.util.base.Constants;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 04-Ene-2012 2:39:00 p.m.
 */
public class ClientRate {

	private int clientRateId;
	private int clientRatePortsId;
	private MasterValue rateType;
	private MasterValue chargeType;
	private double rate;
	private double rateOffset;
	private double minimumRate;
	private double minimumOffset;
	private boolean otherCharge;
	private boolean flat;
	private Date createdDate;
	
	private boolean selectedToAwbDoc;
	
	public ClientRate(){
		chargeType = new MasterValue();
		rateType = new MasterValue();
	}

	public int getClientRateId(){
		return clientRateId;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setClientRateId(int newVal){
		clientRateId = newVal;
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

	public double getRateOffset(){
		return rateOffset;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setRateOffset(double newVal){
		rateOffset = newVal;
	}

	public double getMinimumRate(){
		return minimumRate;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setMinimumRate(double newVal){
		minimumRate = newVal;
	}

	public double getMinimumOffset(){
		return minimumOffset;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setMinimumOffset(double newVal){
		minimumOffset = newVal;
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

	public MasterValue getRateType() {
		return rateType;
	}

	public void setRateType(MasterValue rateType) {
		this.rateType = rateType;
	}

	public MasterValue getChargeType() {
		return chargeType;
	}

	public void setChargeType(MasterValue chargeType) {
		this.chargeType = chargeType;
	}

	public int getClientRatePortsId() {
		return clientRatePortsId;
	}

	public void setClientRatePortsId(int clientRatePortsId) {
		this.clientRatePortsId = clientRatePortsId;
	}

	public boolean isFlat() {
		return flat;
	}

	public void setFlat(boolean flat) {
		this.flat = flat;
	}

	public boolean isSelectedToAwbDoc() {
		return selectedToAwbDoc;
	}

	public void setSelectedToAwbDoc(boolean selectedToAwbDoc) {
		this.selectedToAwbDoc = selectedToAwbDoc;
	}

	public boolean isShowSelectedToAwbDoc() {
		if(chargeType.getValueId() == Constants.MASTER_VALUE_CHARGE_TYPE_AIR_FREIGHT 
				|| chargeType.getValueId() == Constants.MASTER_VALUE_CHARGE_TYPE_DANGEROUS){
			return false;
		}
		return true;
	}
}