package com.lotrading.softlot.businessPartners.dao;
import com.lotrading.softlot.businessPartners.entity.Address;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:54 a.m.
 */
public interface IAddressDao {

	/**
	 * 
	 * @param address
	 * @throws Exception 
	 */
	public int createAddress(Address address) throws Exception;

	/**
	 * 
	 * @param address
	 * @throws Exception 
	 */
	public boolean updateAddress(Address address) throws Exception;

}