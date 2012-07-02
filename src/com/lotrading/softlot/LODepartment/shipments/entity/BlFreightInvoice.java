package com.lotrading.softlot.LODepartment.shipments.entity;

import java.util.Date;

import com.lotrading.softlot.invoice.entity.Invoice;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 27-Sep-2011 12:00:00 AM
 */
public class BlFreightInvoice {

	private int freightInvoiceId;
	private int blId;
	private Invoice invoice;
	
	private Date createdDate;

	public BlFreightInvoice(){
		invoice = new Invoice();
	}
	
	public BlFreightInvoice(int blId){
		this.blId = blId;
		invoice = new Invoice();
	}

	public int getFreightInvoiceId() {
		return freightInvoiceId;
	}

	public void setFreightInvoiceId(int freightInvoiceId) {
		this.freightInvoiceId = freightInvoiceId;
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