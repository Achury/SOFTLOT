package com.lotrading.softlot.warehouse.entity;

import java.util.List;

import com.lotrading.softlot.businessPartners.entity.Partner;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 23-Feb-2012 10:40:00 AM
 */
public class WhReceipt {

	private int whReceiptId;
	private String whReceiptNumber;
	private Partner supplier;
	private Partner client;
	private String supplierRefNo;
	private String clientRefNo;
	private int truckCompanyId;
	private int locationId;
	private String remarks;
	private int employeeRecId;
	private int employeeEntId;
	private int status;
	private int groupId;
	private List<WhItem> whItems;
	
	public WhReceipt(){
		supplier = new Partner();
		client = new Partner();
	}
	
	public int getWhReceiptId() {
		return whReceiptId;
	}
	public void setWhReceiptId(int whReceiptId) {
		this.whReceiptId = whReceiptId;
	}
	public String getWhReceiptNumber() {
		return whReceiptNumber;
	}

	public void setWhReceiptNumber(String whReceiptNumber) {
		this.whReceiptNumber = whReceiptNumber;
	}

	public Partner getSupplier() {
		return supplier;
	}
	public void setSupplier(Partner supplier) {
		this.supplier = supplier;
	}
	public Partner getClient() {
		return client;
	}
	public void setClient(Partner client) {
		this.client = client;
	}
	public String getSupplierRefNo() {
		return supplierRefNo;
	}
	public void setSupplierRefNo(String supplierRefNo) {
		this.supplierRefNo = supplierRefNo;
	}
	public String getClientRefNo() {
		return clientRefNo;
	}
	public void setClientRefNo(String clientRefNo) {
		this.clientRefNo = clientRefNo;
	}
	public int getTruckCompanyId() {
		return truckCompanyId;
	}
	public void setTruckCompanyId(int truckCompanyId) {
		this.truckCompanyId = truckCompanyId;
	}
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public int getEmployeeRecId() {
		return employeeRecId;
	}
	public void setEmployeeRecId(int employeeRecId) {
		this.employeeRecId = employeeRecId;
	}
	public int getEmployeeEntId() {
		return employeeEntId;
	}
	public void setEmployeeEntId(int employeeEntId) {
		this.employeeEntId = employeeEntId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public List<WhItem> getWhItems() {
		return whItems;
	}
	public void setWhItems(List<WhItem> whItems) {
		this.whItems = whItems;
	}	
}
