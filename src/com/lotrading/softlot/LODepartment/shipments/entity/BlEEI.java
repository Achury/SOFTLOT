package com.lotrading.softlot.LODepartment.shipments.entity;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 20-Ene-2012 08:00:00 AM
 */
public class BlEEI {

	private int eeiId;
	private int blId;
	private String eei;
	private String supplier;
	
	public BlEEI(){
		
	}
	public BlEEI(int blId){
		this.blId = blId;
	}
	
	public int getEeiId() {
		return eeiId;
	}
	public void setEeiId(int eeiId) {
		this.eeiId = eeiId;
	}
	public int getBlId() {
		return blId;
	}
	public void setBlId(int blId) {
		this.blId = blId;
	}
	public String getEei() {
		return eei;
	}
	public void setEei(String eei) {
		this.eei = eei;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	
	public boolean isEmpty(){
		if(this.eeiId > 0){
			return false;
		}else if(!this.eei.isEmpty() || !this.supplier.isEmpty()){
			return false;
		}
		return true;
	}
	
}
