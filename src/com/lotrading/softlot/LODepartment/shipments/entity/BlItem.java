package com.lotrading.softlot.LODepartment.shipments.entity;
import java.util.Date;

import com.lotrading.softlot.invoice.entity.Invoice;
import com.lotrading.softlot.setup.entity.MasterValue;
import com.lotrading.softlot.warehouse.entity.WhLocation;
import com.lotrading.softlot.warehouse.entity.WhReceipt;

/**
 * @author JUAN DAVID URIBE - MARLON PINEDA
 * @version 1.0
 * @created 27-Sep-2011 12:00:00 AM
 */
public class BlItem {

	private int itemId;
	private int blId;	
	private int pieces;
	private MasterValue type;
	private double itemLength;
	private double itemWidth;
	private double itemHeight;
	private double itemWeight;	
	private int whItemId;
	private String poNumber;
	private Invoice invoice;
	private String remarks;
	private BlContainer container;	
	private Date createdDate;
	private boolean hazardous;
	private double itemVolume;
	private WhReceipt whReceipt;
	private WhLocation whLocation;
	private int clientOrderId;
	private String palletId;
	

	public BlItem(){
		type = new MasterValue();
		container = new BlContainer();
		invoice = new Invoice();
		whReceipt = new WhReceipt();
		whLocation = new WhLocation();

	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getBlId() {
		return blId;
	}

	public void setBlId(int blId) {
		this.blId = blId;
	}

	public int getPieces() {
		return pieces;
	}

	public void setPieces(int pieces) {
		this.pieces = pieces;
	}

	public MasterValue getType() {
		return type;
	}

	public void setType(MasterValue type) {
		this.type = type;
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

	public double getItemWeight() {
		return itemWeight;
	}

	public void setItemWeight(double itemWeight) {
		this.itemWeight = itemWeight;
	}

	public int getWhItemId() {
		return whItemId;
	}

	public void setWhItemId(int whItemId) {
		this.whItemId = whItemId;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public BlContainer getContainer() {
		return container;
	}

	public void setContainer(BlContainer container) {
		this.container = container;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public boolean isHazardous() {
		return hazardous;
	}

	public void setHazardous(boolean hazardous) {
		this.hazardous = hazardous;
	}
	
	public double getItemVolume() {
		return itemVolume;
	}

	public void setItemVolume(double itemVolume) {
		this.itemVolume = itemVolume;
	}

	public WhReceipt getWhReceipt() {
		return whReceipt;
	}

	public void setWhReceipt(WhReceipt whReceipt) {
		this.whReceipt = whReceipt;
	}

	public WhLocation getWhLocation() {
		return whLocation;
	}

	public void setWhLocation(WhLocation whLocation) {
		this.whLocation = whLocation;
	}
	
	public int getClientOrderId() {
		return clientOrderId;
	}

	public void setClientOrderId(int clientOrderId) {
		this.clientOrderId = clientOrderId;
	}

	public String getPalletId() {
		return palletId;
	}

	public void setPalletId(String palletId) {
		this.palletId = palletId;
	}

	public boolean isEmpty(){
		if(this.pieces == 0 && this.type.getValueId() == 0 ){
			return true;
		}
		return false;
	}

	

}