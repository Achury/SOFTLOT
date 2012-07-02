package com.lotrading.softlot.LODepartment.shipments.entity;

import com.lotrading.softlot.businessPartners.entity.Partner;

/**
 * @author MARLON PINEDA
 * @version 1.0
 * @created 23-Apr-2012 12:00:00 AM
 */
public class BLDeclaredValue {
	private String invoiceNumber;
	private Double totalInvoce;
	private Partner supplier;
	
	public BLDeclaredValue() {
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
