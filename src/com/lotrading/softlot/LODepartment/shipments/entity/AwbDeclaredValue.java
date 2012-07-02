package com.lotrading.softlot.LODepartment.shipments.entity;

import com.lotrading.softlot.businessPartners.entity.Partner;

public class AwbDeclaredValue {

	private String invoiceNumber;
	private Double totalInvoce;
	private Partner supplier;
	
	public AwbDeclaredValue(){
		supplier = new Partner();
	}
	
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public Double getTotalInvoce() {
		return totalInvoce;
	}
	public void setTotalInvoce(Double totalInvoce) {
		this.totalInvoce = totalInvoce;
	}
	public Partner getSupplier() {
		return supplier;
	}
	public void setSupplier(Partner supplier) {
		this.supplier = supplier;
	}
	
}
