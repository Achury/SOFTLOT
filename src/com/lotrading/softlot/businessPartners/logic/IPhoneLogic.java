package com.lotrading.softlot.businessPartners.logic;
import com.lotrading.softlot.businessPartners.entity.Phone;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:56 a.m.
 */
public interface IPhoneLogic {

	/**
	 * 
	 * @param phone
	 * @throws Exception 
	 */
	public int savePhone(Phone phone) throws Exception;

}