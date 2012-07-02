package com.lotrading.softlot.businessPartners.logic;
import com.lotrading.softlot.businessPartners.entity.CourierAccount;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:54 a.m.
 */
public interface ICourierAccountLogic {

	/**
	 * 
	 * @param courierAccount
	 * @throws Exception 
	 */
	public int saveCourierAccount(CourierAccount courierAccount) throws Exception;
	
	/**
	 * 
	 * @param courierAccount
	 * @throws Exception 
	 */
	public boolean deleteCourierAccount(CourierAccount courierAccount) throws Exception;

}