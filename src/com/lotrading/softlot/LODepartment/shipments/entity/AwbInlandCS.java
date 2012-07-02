package com.lotrading.softlot.LODepartment.shipments.entity;
import java.util.Date;

import com.lotrading.softlot.setup.entity.MasterValue;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 20-Ene-2012 08:00:00 AM
 */
public class AwbInlandCS {

	private int inlandCsId;
	private int awbId;
	private double cost;
	private double sale;
	private MasterValue truckCompany;
	private String trackingNumber;
	private Date createdDate;
	private String poNumber;

	public AwbInlandCS(){
		this.truckCompany = new MasterValue();
	}
	
	public AwbInlandCS(int awbId){
		this.awbId = awbId;
		this.truckCompany = new MasterValue();
	}

	public int getInlandCsId(){
		return inlandCsId;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setInlandCsId(int newVal){
		inlandCsId = newVal;
	}

	public double getCost(){
		return cost;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setCost(double newVal){
		cost = newVal;
	}

	public double getSale(){
		return sale;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setSale(double newVal){
		sale = newVal;
	}

	public MasterValue getTruckCompany(){
		return truckCompany;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setTruckCompany(MasterValue newVal){
		truckCompany = newVal;
	}

	public String getTrackingNumber(){
		return trackingNumber;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setTrackingNumber(String newVal){
		trackingNumber = newVal;
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

	public String getPoNumber(){
		return poNumber;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setPoNumber(String newVal){
		poNumber = newVal;
	}

	public int getAwbId() {
		return awbId;
	}

	public void setAwbId(int awbId) {
		this.awbId = awbId;
	}

	public boolean isEmpty(){
		if(this.inlandCsId > 0){
			return false;
		}else if(this.cost >0 ||  this.sale>0){
			return false;
		}
		return true;	
	}
}