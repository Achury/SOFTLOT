package com.lotrading.softlot.LODepartment.shipments.entity;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 20-Ene-2012 08:00:00 AM
 */
public class AwbUnCode {
	
	private int unCodeId;
	private int awbId;
	private String unCode;
	private int unClassId;
	private int packingGroupId;

	public AwbUnCode(){

	}
	public AwbUnCode(int awbId){
		this.awbId = awbId;
	}

	public int getUnCodeId(){
		return unCodeId;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setUnCodeId(int newVal){
		unCodeId = newVal;
	}

	

	public int getAwbId() {
		return awbId;
	}

	public void setAwbId(int awbId) {
		this.awbId = awbId;
	}
	
	public boolean isEmpty(){
		if(this.unCodeId > 0){
			return false;
		}else if(this.unCode == null || this.unCode.isEmpty() || this.getUnClassId() <= 0 || this.getPackingGroupId() <= 0){
			return true;
		}
		return false;	
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
	
	public String getUnCode() {
		return unCode;
	}
	
	public void setUnCode(String unCode) {
		this.unCode = unCode;
	}

}