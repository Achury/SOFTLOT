package com.lotrading.softlot.LODepartment.shipments.entity;

import java.util.Date;

import com.lotrading.softlot.setup.entity.MasterValue;
import com.lotrading.softlot.warehouse.entity.WhLocation;


/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 20-Junio-2012 11:13:00 AM
 */
public class AwbPalletizedItem {

	private int palletizedItemId;
	private String palletId;
	private int awbId;	
	private int pieces;
	private MasterValue type;
	private double itemLength;
	private double itemWidth;
	private double itemHeight;
	private double itemWeight;	
	private String remarks;	
	private Date createdDate;
	private double itemVolume;
	private WhLocation whLocation;
	private MasterValue rateClass;
	
	public AwbPalletizedItem(){
		type = new MasterValue();
		whLocation = new WhLocation();
		rateClass = new MasterValue();
	}
	
	public int getPalletizedItemId() {
		return palletizedItemId;
	}
	public void setPalletizedItemId(int palletizedItemId) {
		this.palletizedItemId = palletizedItemId;
	}
	public String getPalletId() {
		return palletId;
	}
	public void setPalletId(String palletId) {
		this.palletId = palletId;
	}
	public int getAwbId() {
		return awbId;
	}
	public void setAwbId(int awbId) {
		this.awbId = awbId;
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
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public double getItemVolume() {
		return itemVolume;
	}
	public void setItemVolume(double itemVolume) {	
		this.itemVolume = itemHeight * itemLength * itemWidth / 1728;
	}

	public WhLocation getWhLocation() {
		return whLocation;
	}

	public void setWhLocation(WhLocation whLocation) {
		this.whLocation = whLocation;
	}

	public MasterValue getRateClass() {
		return rateClass;
	}

	public void setRateClass(MasterValue rateClass) {
		this.rateClass = rateClass;
	}	
	
}
