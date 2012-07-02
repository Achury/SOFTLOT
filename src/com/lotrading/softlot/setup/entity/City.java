package com.lotrading.softlot.setup.entity;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:51 a.m.
 */
public class City implements Cloneable{

	private Log log = LogFactory.getLog(City.class);
	private int cityId;
	private java.lang.String name;
	private MasterValue stateProvince;
	private MasterValue country;
	private java.util.Date enteredDate;
	private java.util.Date disabledDate;

	public City(){
		stateProvince = new MasterValue();
		country = new MasterValue();
	}
	
	public City(int cityId){
		this.cityId = cityId;
	}

	public void finalize() throws Throwable {

	}

	public int getCityId(){
		return cityId;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setCityId(int newVal){
		cityId = newVal;
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

	public MasterValue getStateProvince(){
		return stateProvince;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setStateProvince(MasterValue newVal){
		stateProvince = newVal;
	}

	public MasterValue getCountry(){
		return country;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setCountry(MasterValue newVal){
		country = newVal;
	}
	
	public java.util.Date getEnteredDate() {
		return enteredDate;
	}

	public void setEnteredDate(java.util.Date enteredDate) {
		this.enteredDate = enteredDate;
	}

	public java.util.Date getDisabledDate() {
		return disabledDate;
	}

	public void setDisabledDate(java.util.Date disabledDate) {
		this.disabledDate = disabledDate;
	}
	
	public Object clone(){
        City obj=null;
        try{
            obj=(City)super.clone();
        }catch(CloneNotSupportedException ex){
        	log.error("no se puede duplicar el la entidad City. An Exception has been thrown " + ex);
        }
        obj.country=(MasterValue)obj.country.clone();
        obj.stateProvince=(MasterValue)obj.stateProvince.clone();
        return obj;
    }

}