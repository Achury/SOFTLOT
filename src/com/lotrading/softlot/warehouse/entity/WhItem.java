package com.lotrading.softlot.warehouse.entity;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.businessPartners.entity.Address;
import com.lotrading.softlot.setup.entity.MasterValue;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 23-Feb-2012 10:40:00 AM
 */
public class WhItem implements Cloneable{

	private Log log = LogFactory.getLog(Address.class);
	private int whItemId;
	private WhReceipt whReceipt;
	private double itemWeight;
	private MasterValue type;
	private String poNumber;
	private int status;
	private double itemLength;
	private double itemWidth;
	private double itemHeight;
	private int pieces;
	private String remarks;
	private boolean hazardous;
	private boolean shipped;
	private String trackingNumber;
	private String locationId;
	private int worksheetId;
	private int clientOrderId;
	
	private int numberPiecesToShip;
	private boolean ship; // sirve cuando se agrega ese item a una guia, cuando se seleciona se agragan todas las piezas.
	
	public WhItem(){
		type = new MasterValue();
		whReceipt = new WhReceipt();
	}
	
	public int getWhItemId() {
		return whItemId;
	}
	public void setWhItemId(int whItemId) {
		this.whItemId = whItemId;
	}
	
	public WhReceipt getWhReceipt() {
		return whReceipt;
	}

	public void setWhReceipt(WhReceipt whReceipt) {
		this.whReceipt = whReceipt;
	}

	public double getItemWeight() {
		return itemWeight;
	}
	public void setItemWeight(double itemWeight) {
		this.itemWeight = itemWeight;
	}
	public MasterValue getType() {
		return type;
	}
	public void setType(MasterValue type) {
		this.type = type;
	}
	public String getPoNumber() {
		return poNumber;
	}
	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public double getItemLength() {
		return itemLength;
	}
	public void setItemLength(double itemLength) {
		this.itemLength = itemLength;
	}
	public double getItemWidth() {
		return itemWidth;
	}
	public void setItemWidth(double itemWidth) {
		this.itemWidth = itemWidth;
	}
	public double getItemHeight() {
		return itemHeight;
	}
	public void setItemHeight(double itemHeight) {
		this.itemHeight = itemHeight;
	}
	public int getPieces() {
		return pieces;
	}
	public void setPieces(int pieces) {
		this.pieces = pieces;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public boolean isHazardous() {
		return hazardous;
	}
	public void setHazardous(boolean hazardous) {
		this.hazardous = hazardous;
	}
	public boolean isShipped() {
		return shipped;
	}
	public void setShipped(boolean shipped) {
		this.shipped = shipped;
	}
	public String getTrackingNumber() {
		return trackingNumber;
	}
	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public int getNumberPiecesToShip() {
		return numberPiecesToShip;
	}
	public void setNumberPiecesToShip(int numberPiecesToShip) {
		this.numberPiecesToShip = numberPiecesToShip;
	}
	public boolean isShip() {
		return ship;
	}
	public void setShip(boolean ship) {
		this.ship = ship;
	}
	public int getWorksheetId() {
		return worksheetId;
	}
	public void setWorksheetId(int worksheetId) {
		this.worksheetId = worksheetId;
	}
	
	public int getClientOrderId() {
		return clientOrderId;
	}

	public void setClientOrderId(int clientOrderId) {
		this.clientOrderId = clientOrderId;
	}

	public Object clone(){
		WhItem obj = null;
		try {
			obj = (WhItem) super.clone();
		} catch (CloneNotSupportedException ex) {
			log.error("can not duplicate the WhItem entity. An Exception has been thrown " + ex);
		}		
		return obj;
	}
}
