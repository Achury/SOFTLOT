package com.lotrading.softlot.businessPartners.entity;
import java.util.Date;

import com.lotrading.softlot.security.entity.Employee;

/**
 * Clase que se utiliza para encapsular la informacion de las llamadas que se le
 * hacen a un Cliente o Proveedor de LO Trading
 * @author MARLON PINEDA
 * @version 1.0
 * @created 01-abr-2011 10:59:50 a.m.
 */
public class CallHistory {

	private int callId;
	private PartnerContact contact;
	private Partner partner;
	private Employee employeeCreator;
	private String notes;
	private Date enteredDate;
	private boolean print;

	public CallHistory(){
		contact = new PartnerContact();
		employeeCreator = new Employee();
		partner = new Partner();
	}

	public int getCallId() {
		return callId;
	}

	public void setCallId(int callId) {
		this.callId = callId;
	}

	public PartnerContact getContact() {
		return contact;
	}

	public void setContact(PartnerContact contact) {
		this.contact = contact;
	}

	public Employee getEmployeeCreator() {
		return employeeCreator;
	}

	public void setEmployeeCreator(Employee employeeCreator) {
		this.employeeCreator = employeeCreator;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(java.lang.String notes) {
		this.notes = notes;
	}

	public Date getEnteredDate() {
		return enteredDate;
	}

	public void setEnteredDate(Date enteredDate) {
		this.enteredDate = enteredDate;
	}

	public Partner getPartner() {
		return partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

	public boolean isPrint() {
		return print;
	}

	public void setPrint(boolean print) {
		this.print = print;
	}
}