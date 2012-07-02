package com.lotrading.softlot.businessPartners.logic;
import java.util.List;

import com.lotrading.softlot.businessPartners.entity.CallHistory;
import com.lotrading.softlot.businessPartners.entity.ClientRatesPort;
import com.lotrading.softlot.businessPartners.entity.CourierAccount;
import com.lotrading.softlot.businessPartners.entity.Partner;
import com.lotrading.softlot.businessPartners.entity.PartnerContact;
import com.lotrading.softlot.businessPartners.entity.PartnerDepartmentInfo;

/**
 * @author Juan Uribe
 * @version 1.0
 * @created 01-abr-2011 10:59:56 a.m.
 */
public interface IPartnerLogic {

	/**
	 * 
	 * @param partner
	 * @throws Exception 
	 */
	public int savePartner(Partner partner) throws Exception;

	/**
	 * 
	 * @param partner
	 * @throws Exception 
	 */
	public Partner searchByCode(Partner partner) throws Exception;
	
	/**
	 * 
	 * @param partner
	 * @throws Exception 
	 */
	public List<Partner> searchPartner(Partner partner) throws Exception;

	public List<Partner> searchLightClient(Partner partner) throws Exception;
	
	
	/**
	 * 
	 * @param partner
	 * @throws Exception 
	 */
	/*public List<ShipPickUp> loadShipPickup(Partner partner) throws Exception;*/

	/**
	 * 
	 * @param partner
	 * @throws Exception 
	 */
	public List<CourierAccount> loadCourierAccount(Partner partner) throws Exception;

	/**
	 * 
	 * @param partner
	 */
	public List<PartnerContact> loadContacts(Partner partner)throws Exception;

	/**
	 * 
	 * @param partner
	 */
	public PartnerDepartmentInfo loadDepartmentInfo(Partner partner)throws Exception;

	/**
	 * 
	 * @param partner
	 */
	public List<CallHistory> loadCallHistory(Partner partner)throws Exception;
	
	public List<ClientRatesPort> loadClientRates(ClientRatesPort clientRatesPort) throws Exception;
	
	public PartnerContact loadPartnerContactMainInfo(PartnerContact partnerContact) throws Exception;
}