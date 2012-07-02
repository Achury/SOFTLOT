package com.lotrading.softlot.LODepartment.shipments.entity;
import com.lotrading.softlot.setup.entity.MasterValue;

/**
 * @author MARLON PINEDA
 * @version 1.0
 * @created 9-March-2012 12:00:00 AM
 */
public class BlContainer {

	private int containerId;
	private int blId;		
	private MasterValue type;
	private int lineNumber;	
	private String containerNumber;	
	private String seal;
	private int pieces;
	private double grossWeight;
	private double itemsVolume;
	
	

	public BlContainer(){
		type = new MasterValue();
	}
	
	public BlContainer(int blId, int lineNumber){
		this.blId = blId;
		this.lineNumber = lineNumber;
		type = new MasterValue();
	}

	public int getContainerId() {
		return containerId;
	}

	public void setContainerId(int containerId) {
		this.containerId = containerId;
	}

	public int getBlId() {
		return blId;
	}

	public void setBlId(int blId) {
		this.blId = blId;
	}
	
	public MasterValue getType() {
		return type;
	}

	public void setType(MasterValue type) {
		this.type = type;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public String getContainerNumber() {
		return containerNumber;
	}

	public void setContainerNumber(String containerNumber) {
		this.containerNumber = containerNumber;
	}

	public String getSeal() {
		return seal;
	}

	public void setSeal(String seal) {
		this.seal = seal;
	}

	public int getPieces() {
		return pieces;
	}

	public void setPieces(int pieces) {
		this.pieces = pieces;
	}

	public double getGrossWeight() {
		return grossWeight;
	}

	public void setGrossWeight(double grossWeight) {
		this.grossWeight = grossWeight;
	}

	public double getItemsVolume() {
		return itemsVolume;
	}

	public void setItemsVolume(double itemsVolume) {
		this.itemsVolume = itemsVolume;
	}

	public boolean isEmpty(){
		if((this.containerNumber == null) || (this.containerNumber.equals(""))){
			return true;
		}
		return false;
	}

}