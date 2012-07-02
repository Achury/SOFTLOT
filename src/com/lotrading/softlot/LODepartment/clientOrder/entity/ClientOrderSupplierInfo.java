package com.lotrading.softlot.LODepartment.clientOrder.entity;

import com.lotrading.softlot.setup.entity.MasterValue;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 27-Sep-2011 12:00:00 AM
 */
public class ClientOrderSupplierInfo {

	private int clientOrderSupplierInfoId;
	private int clientOrderId;
	private String supplierInvoiceNum;
	private double totalSupplierInvoice;
	private MasterValue invoiceType;

	public ClientOrderSupplierInfo() {
		invoiceType = new MasterValue();
	}

	public int getClientOrderId() {
		return clientOrderId;
	}

	public void setClientOrderId(int clientOrderId) {
		this.clientOrderId = clientOrderId;
	}

	public String getSupplierInvoiceNum() {
		return supplierInvoiceNum;
	}

	public void setSupplierInvoiceNum(String supplierInvoiceNum) {
		this.supplierInvoiceNum = supplierInvoiceNum;
	}

	public double getTotalSupplierInvoice() {
		return totalSupplierInvoice;
	}

	public void setTotalSupplierInvoice(double totalSupplierInvoice) {
		this.totalSupplierInvoice = Math.round( totalSupplierInvoice * 100.0 ) / 100.0;;
	}

	public int getClientOrderSupplierInfoId() {
		return clientOrderSupplierInfoId;
	}

	public void setClientOrderSupplierInfoId(int clientOrderSupplierInfoId) {
		this.clientOrderSupplierInfoId = clientOrderSupplierInfoId;
	}

	public boolean isEmpty(){
		if(this.supplierInvoiceNum == null && this.totalSupplierInvoice == 0){
			return true;
		}else if(this.supplierInvoiceNum.isEmpty() && this.totalSupplierInvoice == 0){
			return true;
		}
		return false;
	}

	public MasterValue getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(MasterValue invoiceType) {
		this.invoiceType = invoiceType;
	}
}