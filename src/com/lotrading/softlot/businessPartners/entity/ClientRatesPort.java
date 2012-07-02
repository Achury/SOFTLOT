package com.lotrading.softlot.businessPartners.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lotrading.softlot.setup.entity.MasterValue;


/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 13-Ene-2012 2:39:00 p.m.
 */

public class ClientRatesPort {

	private int clientRatePortId;
	private int clientId;
	private int carrierId;
	private int portDestination;
	private int portOrigin;
	private MasterValue rateType;
	private MasterValue chargeType;
	private List<ClientRate> clientRates;
	private Date created;
	
	public ClientRatesPort(){
		clientRates = new ArrayList<ClientRate>();
		rateType = new MasterValue();
		chargeType = new MasterValue();
	}
	
	public int getClientRatePortId() {
		return clientRatePortId;
	}
	public void setClientRatePortId(int clientRatePortId) {
		this.clientRatePortId = clientRatePortId;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public int getCarrierId() {
		return carrierId;
	}
	public void setCarrierId(int carrierId) {
		this.carrierId = carrierId;
	}
	public int getPortDestination() {
		return portDestination;
	}
	public void setPortDestination(int portDestination) {
		this.portDestination = portDestination;
	}
	public int getPortOrigin() {
		return portOrigin;
	}
	public void setPortOrigin(int portOrigin) {
		this.portOrigin = portOrigin;
	}
	public List<ClientRate> getClientRates() {
		return clientRates;
	}
	public void setClientRates(List<ClientRate> clientRates) {
		this.clientRates = clientRates;
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

	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
}
