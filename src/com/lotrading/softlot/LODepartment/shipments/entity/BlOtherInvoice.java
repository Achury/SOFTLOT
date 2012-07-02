package com.lotrading.softlot.LODepartment.shipments.entity;

import java.util.Date;

import com.lotrading.softlot.invoice.entity.Invoice;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 27-Sep-2011 12:00:00 AM
 */
public class BlOtherInvoice {

	private int blId;
	private int otherInvoiceId;
	private Invoice invoice;
	private Date createdDate;

	public BlOtherInvoice(){
		invoice = new Invoice();
	}
	
	public BlOtherInvoice(int blId){
		invoice = new Invoice();
		this.blId = blId;
	}
	
	public int getOtherInvoiceId() {
		return otherInvoiceId;
	}

	public void setOtherInvoiceId(int otherInvoiceId) {
		this.otherInvoiceId = otherInvoiceId;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getBlId() {
		return blId;
	}

	public void setBlId(int blId) {
		this.blId = blId;
	}

	

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	
	
	public boolean isEmpty(){
		return (this.invoice.getInvoiceId() <= 0);
	}
	

}