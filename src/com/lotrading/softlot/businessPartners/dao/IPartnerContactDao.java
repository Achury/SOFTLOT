package com.lotrading.softlot.businessPartners.dao;
import java.util.List;

import com.lotrading.softlot.businessPartners.entity.PartnerContact;
import com.lotrading.softlot.businessPartners.entity.Phone;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:55 a.m.
 */
public interface IPartnerContactDao {

	/**
	 * 
	 * @param partnerContact
	 * @throws Exception 
	 */
	public int createContact(PartnerContact partnerContact) throws Exception;

	/**
	 * 
	 * @param partnerContact
	 * @throws Exception 
	 */
	public boolean updatePartnerContact(PartnerContact partnerContact) throws Exception;

	/**
	 * 
	 * @param partnerContact
	 * @throws Exception 
	 */
	public List<Phone> loadContactPhones(PartnerContact partnerContact) throws Exception;

	public boolean saveContactPhone(int phoneId, int contactId, boolean isMain) throws Exception;

	public int getDeptInfoID(PartnerContact partnerContact)throws Exception;
	

}