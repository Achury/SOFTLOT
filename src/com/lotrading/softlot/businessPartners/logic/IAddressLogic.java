package com.lotrading.softlot.businessPartners.logic;
import com.lotrading.softlot.businessPartners.entity.Address;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:54 a.m.
 */
public interface IAddressLogic {

	/**
	 * 
	 * @param address
	 * @throws Exception 
	 */
	public int saveAddress(Address address) throws Exception;

}