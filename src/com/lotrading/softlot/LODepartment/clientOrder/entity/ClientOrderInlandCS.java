package com.lotrading.softlot.LODepartment.clientOrder.entity;
import com.lotrading.softlot.setup.entity.MasterValue;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 27-Sep-2011 12:00:00 AM
 */
public class ClientOrderInlandCS {

	private int clientOrderInlandCSId;
	private int clientOrderId;
	private MasterValue truckCompany;
	private double cost;
	private double sales;
	private String trackingNumber;

	public ClientOrderInlandCS(){
		truckCompany = new MasterValue();
	}

	public int getClientOrderInlandCSId() {
		return clientOrderInlandCSId;
	}

	public void setClientOrderInlandCSId(int clientOrderInlandCSId) {
		this.clientOrderInlandCSId = clientOrderInlandCSId;
	}

	public int getClientOrderId() {
		return clientOrderId;
	}

	public void setClientOrderId(int clientOrderId) {
		this.clientOrderId = clientOrderId;
	}

	public MasterValue getTruckCompany() {
		return truckCompany;
	}

	public void setTruckCompany(MasterValue truckCompany) {
		this.truckCompany = truckCompany;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = Math.round( cost * 100.0 ) / 100.0;  // o lo que es lo mismo  Math.round(cost*Math.pow(10,2))/Math.pow(10,2);
	}

	public double getSales() {
		return sales;
	}

	public void setSales(double sales) {
		this.sales = Math.round( sales * 100.0 ) / 100.0;
	}

	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public boolean isEmpty(){
		if(this.cost == 0 && this.sales == 0 && this.truckCompany.getValueId() == 0 && this.trackingNumber == null){
			return true;
		}
		if(this.trackingNumber != null ){
			if(this.cost == 0 && this.sales == 0 && this.truckCompany.getValueId() == 0 && this.trackingNumber.isEmpty()){
				return true;
			}
		}
		return false;
	}
}