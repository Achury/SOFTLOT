package com.lotrading.softlot.LODepartment.shipments.entity;

import java.util.Date;
import com.lotrading.softlot.setup.entity.MasterValue;
import com.lotrading.softlot.warehouse.entity.WhLocation;

/**
 * @author MARLON PINEDA
 * @version 1.0
 * @created 19-June-2012 12:00:00 AM
 */
public class BlPalletizedItem {

	private int palletizedItemId;
	private String palletId;
	private int blId;
	private int pieces;
	private MasterValue type;
	private double itemLength;
	private double itemWidth;
	private double itemHeight;
	private double itemWeight;
	private String remarks;
	private BlContainer container;
	private Date createdDate;
	private WhLocation whLocation;
	private boolean hazardous;
	private double itemVolume;

	public BlPalletizedItem() {
		type = new MasterValue();
		container = new BlContainer();
		whLocation = new WhLocation();

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

	public void setPalletId(String externalId) {
		this.palletId = externalId;
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

	public WhLocation getWhLocation() {
		return whLocation;
	}

	public void setWhLocation(WhLocation whLocation) {
		this.whLocation = whLocation;
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

	public boolean isEmpty() {
		if (this.pieces == 0 && this.type.getValueId() == 0) {
			return true;
		}
		return false;
	}

}