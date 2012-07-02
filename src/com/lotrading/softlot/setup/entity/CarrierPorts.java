package com.lotrading.softlot.setup.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CarrierPorts {

	private int carrierPortId;
	private int portOrigin;
	private int portDestination;
	private int carrierId;
	private MasterValue rateType;
	private MasterValue chargeType;
	private List<CarrierRate> carrierRates;
	private Date effectiveDate;
	
	public CarrierPorts() {
		rateType = new MasterValue();
		carrierRates = new ArrayList<CarrierRate>();
	}
	
	public int getCarrierPortId() {
		return carrierPortId;
	}
	public void setCarrierPortId(int carrierPortId) {
		this.carrierPortId = carrierPortId;
	}
	public int getPortOrigin() {
		return portOrigin;
	}
	public void setPortOrigin(int portOrigin) {
		this.portOrigin = portOrigin;
	}
	public int getPortDestination() {
		return portDestination;
	}
	public void setPortDestination(int portDestination) {
		this.portDestination = portDestination;
	}
	public int getCarrierId() {
		return carrierId;
	}
	public void setCarrierId(int carrierId) {
		this.carrierId = carrierId;
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

	public List<CarrierRate> getCarrierRates() {
		return carrierRates;
	}
	public void setCarrierRates(List<CarrierRate> carrierRates) {
		this.carrierRates = carrierRates;
	}
	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	
}
