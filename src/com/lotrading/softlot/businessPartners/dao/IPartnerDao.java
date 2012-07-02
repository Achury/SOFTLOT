package com.lotrading.softlot.businessPartners.dao;
import java.util.List;

import com.lotrading.softlot.businessPartners.entity.CallHistory;
import com.lotrading.softlot.businessPartners.entity.ClientRate;
import com.lotrading.softlot.businessPartners.entity.ClientRatesPort;
import com.lotrading.softlot.businessPartners.entity.CourierAccount;
import com.lotrading.softlot.businessPartners.entity.Partner;
import com.lotrading.softlot.businessPartners.entity.PartnerContact;
import com.lotrading.softlot.businessPartners.entity.PartnerDepartmentInfo;
import com.lotrading.softlot.businessPartners.entity.ShipPickUp;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:55 a.m.
 */
public interface IPartnerDao {

	/**
	 * 
	 * @param partner
	 * @throws Exception 
	 */
	public int createPartner(Partner partner) throws Exception;

	/**
	 * 
	 * @param partner
	 * @throws Exception 
	 */
	public boolean updatePartner(Partner partner) throws Exception;

	/**
	 * 
	 * @param partner
	 * @throws Exception 
	 */
	public List<Partner> searchPartner(Partner partner) throws Exception;

	/**
	 * 
	 * @param partner
	 * @throws Exception 
	 */
	public List<PartnerContact> loadPartnerContacts(Partner partner) throws Exception;

	/**
	 * 
	 * @param partner
	 * @throws Exception 
	 */
	public List<CourierAccount> loadCourierAccounts(Partner partner) throws Exception;

	/**
	 * 
	 * @param partner
	 * @throws Exception 
	 */
	public List<ShipPickUp> loadShipPickup(Partner partner) throws Exception;

	/**
	 * 
	 * @param partner
	 * @throws Exception 
	 */
	public PartnerDepartmentInfo loadDeparmentInfo(Partner partner) throws Exception;

	/**
	 * 
	 * @param partner
	 * @throws Exception 
	 */
	public List<CallHistory> loadCallHistory(Partner partner) throws Exception;

	/**
	 * 
	 * @param partner
	 * @throws Exception 
	 */
	public Partner loadByCode(Partner partner) throws Exception;

	
	public List<Partner> searchLightClient(Partner partner) throws Exception;
	
	public List<ClientRate> loadClientRates(ClientRate clientRate) throws Exception;
	
	public List<ClientRatesPort> loadClientRatesPort(ClientRatesPort clientRatesPort) throws Exception;
	
	public PartnerContact loadPartnerContactMainInfo(PartnerContact partnerContact) throws Exception;
}