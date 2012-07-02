package com.lotrading.softlot.businessPartners.entity;
import com.lotrading.softlot.setup.entity.City;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Clase que se utiliza para encapsular la informacion de las direcciones
 * @author MARLON PINEDA
 * @version 1.0
 * @created 01-abr-2011 10:59:49 a.m.
 */
public class Address implements Cloneable{

	private Log log = LogFactory.getLog(Address.class);
	private int addressId;
	private java.lang.String address;
	private City city;
	private java.lang.String postalCode;

	public Address(){
		city = new City();

	}

	public Address(int newVal){
		this.addressId = newVal;
	}

	public int getAddressId(){
		return addressId;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setAddressId(int newVal){
		addressId = newVal;
	}

	public java.lang.String getAddress(){
		return address;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setAddress(java.lang.String newVal){
		address = newVal;
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

	public java.lang.String getPostalCode(){
		return postalCode;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setPostalCode(java.lang.String newVal){
		postalCode = newVal;
	}
	
	public Object clone(){
        Address obj=null;
        try{
            obj=(Address)super.clone();
        }catch(CloneNotSupportedException ex){
        	log.error("no se puede duplicar el la entidad Address. An Exception has been thrown " + ex);
        }
        obj.city=(City)obj.city.clone();
        return obj;
    }

}