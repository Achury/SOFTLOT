package com.lotrading.softlot.LODepartment.shipments.entity;
import java.util.Date;

import com.lotrading.softlot.setup.entity.MasterValue;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 27-Sep-2011 12:00:00 AM
 */
public class BlInlandCS {

	private int inlandCSId;
	private int blId;
	private double cost;
	private double sale;
	private MasterValue truckCompany;
	private String trackingNumber;
	private Date createdDate;
	private String poNumber;

	public BlInlandCS(){
		truckCompany = new MasterValue();
	}
	
	public BlInlandCS(int blId){
		this.blId=blId;
		truckCompany = new MasterValue();
	}

	public int getInlandCSId() {
		return inlandCSId;
	}

	public void setInlandCSId(int inlandCsId) {
		this.inlandCSId = inlandCsId;
	}

	public int getBlId() {
		return blId;
	}

	public void setBlId(int blId) {
		this.blId = blId;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getSale() {
		return sale;
	}

	public void setSale(double sale) {
		this.sale = sale;
	}

	public MasterValue getTruckCompany() {
		return truckCompany;
	}

	public void setTruckCompany(MasterValue truckCompany) {
		this.truckCompany = truckCompany;
	}

	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}
	
	public boolean isEmpty(){
		if(this.inlandCSId > 0){
			return false;
		}else if(this.cost >0 ||  this.sale>0){
			return false;
		}
		return true;	
	}

	
}