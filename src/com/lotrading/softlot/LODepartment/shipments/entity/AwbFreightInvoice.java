package com.lotrading.softlot.LODepartment.shipments.entity;

import java.util.Date;

import com.lotrading.softlot.invoice.entity.Invoice;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 20-Ene-2012 08:00:00 AM
 */
public class AwbFreightInvoice {

	private int freightInvoiceId;
	private int awbId;
	private Invoice invoice;
	private Date createdDate;

	public AwbFreightInvoice(){
		invoice = new Invoice();
	}
	
	public AwbFreightInvoice(int awbId){
		this.awbId = awbId;
		invoice = new Invoice();
	}

	public int getFreightInvoiceId(){
		return freightInvoiceId;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setFreightInvoiceId(int newVal){
		freightInvoiceId = newVal;
	}

	public Date getCreatedDate(){
		return createdDate;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setCreatedDate(Date newVal){
		createdDate = newVal;
	}

	public int getAwbId() {
		return awbId;
	}

	public void setAwbId(int awbId) {
		this.awbId = awbId;
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