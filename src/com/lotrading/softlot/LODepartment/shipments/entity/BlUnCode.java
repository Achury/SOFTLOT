package com.lotrading.softlot.LODepartment.shipments.entity;

/**
 * @author MARLON PINEDA
 * @version 1.0
 * @created 25-Abril-2012 12:00:00 AM
 */
public class BlUnCode {

	private int unCodeId;
	private int blId;
	private String unCode;
	private int unClassId;
	private int packingGroupId;

	public BlUnCode(){

	}
	public BlUnCode(int blId){
		this.blId = blId;
	}

	public int getUnCodeId() {
		return unCodeId;
	}

	public void setUnCodeId(int unCodeId) {
		this.unCodeId = unCodeId;
	}

	public int getBlId() {
		return blId;
	}

	public void setBlId(int blId) {
		this.blId = blId;
	}

	public String getUnCode() {
		return unCode;
	}

	public void setUnCode(String unCode) {
		this.unCode = unCode;
	}

	public int getUnClassId() {
		return unClassId;
	}

	public void setUnClassId(int unClassId) {
		this.unClassId = unClassId;
	}

	public int getPackingGroupId() {
		return packingGroupId;
	}

	public void setPackingGroupId(int packingGroupId) {
		this.packingGroupId = packingGroupId;
	}
	
	public boolean isEmpty(){
		if(this.unCodeId > 0){
			return false;
		}else{ 
			if(this.unCode != null){	
				if(!this.unCode.isEmpty()) return false;
			}
		}
		return true;	
	}
	

}