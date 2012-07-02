package com.lotrading.softlot.setup.entity;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 11:00:01 a.m.
 */
public class Port {

	private int portId;
	private java.lang.String name;
	private City city;
	private java.lang.String iataCode;
	private boolean isAir;
	private java.util.Date enteredDate;
	private java.util.Date disabledDate;

	public Port(){
		city = new City();
	}

	

	public void finalize() throws Throwable {

	}

	public int getPortId(){
		return portId;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setPortId(int newVal){
		portId = newVal;
	}

	public java.lang.String getName(){
		return name;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setName(java.lang.String newVal){
		name = newVal;
	}

	public City getCity(){
		return city;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setCity(City newVal){
		city = newVal;
	}

	public java.lang.String getIataCode(){
		return iataCode;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setIataCode(java.lang.String newVal){
		iataCode = newVal;
	}

	

	public boolean isAir() {
		return isAir;
	}

	public void setAir(boolean isAir) {
		this.isAir = isAir;
	}



	public void setEnteredDate(java.util.Date enteredDate) {
		this.enteredDate = enteredDate;
	}

	public java.util.Date getEnteredDate() {
		return enteredDate;
	}
	public java.util.Date getDisabledDate() {
		return disabledDate;
	}

	public void setDisabledDate(java.util.Date disableDate) {
		this.disabledDate = disableDate;
	}

}