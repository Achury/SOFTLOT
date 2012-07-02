package com.lotrading.softlot.businessPartners.entity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Clase que se utiliza para encapsular los contactos de un Cliente o Proveedor de
 * LO Trading
 * @author MARLON PINEDA
 * @version 1.0
 * @created 01-abr-2011 10:59:59 a.m.
 */
public class PartnerContact {

	private int contactId;
	private String name;
	private String title;
	private Address address;
	private List<Phone> phones;
	private String email;
	private boolean main;
	private String notes;
	private int employeeCreator;
	private Date enteredDate;
	private Date disableDate;
	private int partnerDeptInfoId;
	private int departmentLotId;
	private int partnerId;
	private String concatenatedPhones;

	public PartnerContact(){
		address = new Address();
		phones = new ArrayList<Phone>();
	}

	
	public String getName(){
		return name;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setName(String newVal){
		name = newVal;
	}

	public String getTitle(){
		return title;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setTitle(String newVal){
		title = newVal;
	}

	public String getEmail(){
		return email;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setEmail(String newVal){
		email = newVal;
	}

	public String getNotes(){
		return notes;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setNotes(String newVal){
		notes = newVal;
	}

	public int getEmployeeCreator(){
		return employeeCreator;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setEmployeeCreator(int newVal){
		employeeCreator = newVal;
	}

	public Date getEnteredDate(){
		return enteredDate;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setEnteredDate(Date newVal){
		enteredDate = newVal;
	}

	public Date getDisableDate(){
		return disableDate;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setDisableDate(Date newVal){
		disableDate = newVal;
	}

	public int getContactId(){
		return contactId;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setContactId(int newVal){
		contactId = newVal;
	}

	public Address getAddress(){
		return address;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setAddress(Address newVal){
		address = newVal;
	}

	public List<Phone> getPhones(){
		return phones;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setPhones(List<Phone> newVal){
		phones = newVal;
	}

	public boolean isMain(){
		return main;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setMain(boolean newVal){
		main = newVal;
	}
	
	public int getPartnerDeptInfoId() {
		return partnerDeptInfoId;
	}

	public void setPartnerDeptInfoId(int partnerDeptInfoId) {
		this.partnerDeptInfoId = partnerDeptInfoId;
	}


	public int getDepartmentLotId() {
		return departmentLotId;
	}


	public void setDepartmentLotId(int departmentLotId) {
		this.departmentLotId = departmentLotId;
	}


	public int getPartnerId() {
		return partnerId;
	}


	public void setPartnerId(int partnerId) {
		this.partnerId = partnerId;
	}


	public String getConcatenatedPhones() {
		return concatenatedPhones;
	}


	public void setConcatenatedPhones(String concatenatedPhones) {
		this.concatenatedPhones = concatenatedPhones;
	}

}