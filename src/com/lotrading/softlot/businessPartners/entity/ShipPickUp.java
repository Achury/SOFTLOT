package com.lotrading.softlot.businessPartners.entity;


/**
 * Clase que se utiliza para encapsular la informacion de las direcciones donde se
 * recoje la mercancia de un Proveedor  o donde se envia la de un  Cliente de LO
 * Trading
 * @author MARLON PINEDA
 * @version 1.0
 * @created 01-abr-2011 11:00:03 a.m.
 */
public class ShipPickUp {

	private int shipPickUpId;
	//private int partnerDeptInfoId;
	private String name;
	private Address address;
	private int destinationAirport;
	private int destinationPort;
	private java.util.Date disabledDate;
	private String email;
	private java.util.Date enteredDate;
	private String notes;
	private String notifyParty;
	//private int departmentLotId;
	private int partnerId;

	public ShipPickUp(){
		address = new Address();
	}
	
	public int getShipPickUpId(){
		return shipPickUpId;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setShipPickUpId(int newVal){
		shipPickUpId = newVal;
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

	public String getNotifyParty(){
		return notifyParty;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setNotifyParty(String newVal){
		notifyParty = newVal;
	}

	public java.util.Date getEnteredDate(){
		return enteredDate;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setEnteredDate(java.util.Date newVal){
		enteredDate = newVal;
	}

	public java.util.Date getDisabledDate(){
		return disabledDate;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setDisabledDate(java.util.Date newVal){
		disabledDate = newVal;
	}

	public Address getAddress(){
		return address;
	}

	public int getDestinationAirport(){
		return destinationAirport;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setAddress(Address newVal){
		address = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setDestinationAirport(int newVal){
		destinationAirport = newVal;
	}

	public int getDestinationPort(){
		return destinationPort;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setDestinationPort(int newVal){
		destinationPort = newVal;
	}

/*	public void setPartnerDeptInfoId(int partnerDeptInfoId) {
		this.partnerDeptInfoId = partnerDeptInfoId;
	}

	public int getPartnerDeptInfoId() {
		return partnerDeptInfoId;
	}*/

	/*public int getDepartmentLotId() {
		return departmentLotId;
	}

	public void setDepartmentLotId(int departmentLotId) {
		this.departmentLotId = departmentLotId;
	}*/

	public int getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(int partnerId) {
		this.partnerId = partnerId;
	}

}