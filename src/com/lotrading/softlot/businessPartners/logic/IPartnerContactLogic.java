package com.lotrading.softlot.businessPartners.logic;
import java.util.List;

import com.lotrading.softlot.businessPartners.entity.PartnerContact;
import com.lotrading.softlot.businessPartners.entity.Phone;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:55 a.m.
 */
public interface IPartnerContactLogic {

	/**
	 * 
	 * @param partnerContact
	 * @throws Exception 
	 */
	public int savePartnerContact(PartnerContact partnerContact) throws Exception;

	/**
	 * 
	 * @param PartnerContact
	 */
	public List<Phone> loadContactPhones(PartnerContact PartnerContact) throws Exception;
	
	public List<Phone> saveContactPhones(PartnerContact partnerContact) throws Exception;

}