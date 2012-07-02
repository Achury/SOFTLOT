package com.lotrading.softlot.businessPartners.dao;
import com.lotrading.softlot.businessPartners.entity.CourierAccount;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:54 a.m.
 */
public interface ICourierAccountDao {

	/**
	 * 
	 * @param courierAccount
	 * @throws Exception 
	 */
	public int createCourierAccount(CourierAccount courierAccount) throws Exception;

	/**
	 * 
	 * @param courierAccount
	 * @throws Exception 
	 */
	public boolean updateCourierAccount(CourierAccount courierAccount) throws Exception;
	
	/**
	 * 
	 * @param courierAccount
	 * @throws Exception 
	 */
	public boolean deleteCourierAccount(CourierAccount courierAccount) throws Exception;

}