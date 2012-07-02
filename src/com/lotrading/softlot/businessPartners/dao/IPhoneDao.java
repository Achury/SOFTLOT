package com.lotrading.softlot.businessPartners.dao;
import com.lotrading.softlot.businessPartners.entity.Phone;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:56 a.m.
 */
public interface IPhoneDao {

	/**
	 * 
	 * @param phone
	 * @throws Exception 
	 */
	public int createPhone(Phone phone) throws Exception;

	/**
	 * 
	 * @param phone
	 * @throws Exception 
	 */
	public boolean updatePhone(Phone phone) throws Exception;

}