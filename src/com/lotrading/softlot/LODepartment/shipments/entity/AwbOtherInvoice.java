package com.lotrading.softlot.LODepartment.shipments.entity;

import java.util.Date;

import com.lotrading.softlot.invoice.entity.Invoice;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 20-Ene-2012 08:00:00 AM
 */
public class AwbOtherInvoice {

	private int awbId;
	private int otherInvoiceId;
	private Invoice invoice;
	private Date createdDate;

	public AwbOtherInvoice(){
		invoice = new Invoice();
	}
	
	public AwbOtherInvoice(int awbId){
		invoice = new Invoice();
		this.awbId = awbId;
	}

	public int getOtherInvoiceId(){
		return otherInvoiceId;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setOtherInvoiceId(int newVal){
		otherInvoiceId = newVal;
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
	
	public boolean isEmpty(){
		return (this.invoice.getInvoiceId() <= 0);
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

}