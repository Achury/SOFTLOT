package com.lotrading.softlot.setup.entity;

import java.util.Date;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 27-Sep-2011 12:00:00 AM
 */
public class CarrierAwbNumber {

	private int carrierAwbNumberId;
	private String awbNumber;
	private Carrier carrier;
	private boolean used;
	private Date createdDate;

	public CarrierAwbNumber(){
		carrier = new Carrier();
	}
	
	public CarrierAwbNumber(String awbNum){
		this.awbNumber = awbNum;
	}
	
	public CarrierAwbNumber(String awbNum , int carrierId){
		this.awbNumber = awbNum;
		carrier = new Carrier();
		carrier.setCarrierId(carrierId);
	}

	public int getCarrierAwbNumberId(){
		return carrierAwbNumberId;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setCarrierAwbNumberId(int newVal){
		carrierAwbNumberId = newVal;
	}

	public String getAwbNumber(){
		return awbNumber;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setAwbNumber(String newVal){
		awbNumber = newVal;
	}

	public boolean isUsed(){
		return used;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setUsed(boolean newVal){
		used = newVal;
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

	public Carrier getCarrier() {
		return carrier;
	}

	public void setCarrier(Carrier carrier) {
		this.carrier = carrier;
	}

}