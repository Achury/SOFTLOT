package com.lotrading.softlot.businessPartners.logic.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.businessPartners.dao.IPartnerDao;
import com.lotrading.softlot.businessPartners.entity.CallHistory;
import com.lotrading.softlot.businessPartners.entity.ClientRate;
import com.lotrading.softlot.businessPartners.entity.ClientRatesPort;
import com.lotrading.softlot.businessPartners.entity.CourierAccount;
import com.lotrading.softlot.businessPartners.entity.Partner;
import com.lotrading.softlot.businessPartners.entity.PartnerContact;
import com.lotrading.softlot.businessPartners.entity.PartnerDepartmentInfo;
import com.lotrading.softlot.businessPartners.logic.IAddressLogic;
import com.lotrading.softlot.businessPartners.logic.IPartnerDepartmentInfoLogic;
import com.lotrading.softlot.businessPartners.logic.IPartnerLogic;
import com.lotrading.softlot.businessPartners.logic.IPhoneLogic;
import com.lotrading.softlot.util.base.Constants;



/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 11:00:00 a.m.
 */
public class PartnerLogicImpl implements IPartnerLogic {

	private IPartnerDao partnerDao;
	private Log log = LogFactory.getLog(PartnerLogicImpl.class);	
	private IPhoneLogic phoneLogic;
	private IAddressLogic addressLogic;
	private IPartnerDepartmentInfoLogic partnerDepartmentInfoLogic;	
	
	public PartnerLogicImpl(){

	}
	
	/**
	 * 
	 * @param partner
	 */
	public int savePartner(Partner partner) throws Exception{
		int _tmpPartnerId = 0;
		
		try {
			if (partner == null)
				return _tmpPartnerId;
			
			if (partner.getAddress() != null){				
				partner.getAddress().setAddressId( addressLogic.saveAddress(partner.getAddress()));				
			}
			
			if (partner.getFax() != null){				
				partner.getFax().setPhoneId( phoneLogic.savePhone(partner.getFax()));				
			}
			
			if (partner.getPhone() != null){				
				partner.getPhone().setPhoneId( phoneLogic.savePhone(partner.getPhone()));				
			}
			// if Partner is New
			if (partner.getPartnerId() <= 0) {				
				partner.setEnteredDate(new Date());
				partner.setUpdatedDate(new Date());
				
				// if the partner is client and it does not have client code then return 0; it has to have a code
				if  ( (partner.isClient() ) && ( partner.getCode().isEmpty() ||  partner.getCode().equals("") || partner.getCode()== null  )){
					return 0;
				}else{
					_tmpPartnerId = partnerDao.createPartner(partner);
					//is the partner was successfully created then will create the info by department
					if (_tmpPartnerId > 0) {
						
						PartnerDepartmentInfo tmp_depInfoGeneral = new PartnerDepartmentInfo();
						tmp_depInfoGeneral.setPartnerId(_tmpPartnerId);
						tmp_depInfoGeneral.setDepartmentLotId(Constants.MASTER_VALUE_DEPARTMENT_EVERYONE);
						partnerDepartmentInfoLogic.saveDepartmentInfo(tmp_depInfoGeneral);
						
						PartnerDepartmentInfo tmp_depInfoRM = new PartnerDepartmentInfo();
						tmp_depInfoRM.setPartnerId(_tmpPartnerId);
						tmp_depInfoRM.setDepartmentLotId(Constants.MASTER_VALUE_DEPARTMENT_RM);
						partnerDepartmentInfoLogic.saveDepartmentInfo(tmp_depInfoRM);
						
						PartnerDepartmentInfo tmp_depInfoIP = new PartnerDepartmentInfo();
						tmp_depInfoIP.setPartnerId(_tmpPartnerId);
						tmp_depInfoIP.setDepartmentLotId(Constants.MASTER_VALUE_DEPARTMENT_IP);
						partnerDepartmentInfoLogic.saveDepartmentInfo(tmp_depInfoIP);
						
						PartnerDepartmentInfo tmp_depInfoFF = new PartnerDepartmentInfo();
						tmp_depInfoFF.setPartnerId(_tmpPartnerId);
						tmp_depInfoFF.setDepartmentLotId(Constants.MASTER_VALUE_DEPARTMENT_FF);
						partnerDepartmentInfoLogic.saveDepartmentInfo(tmp_depInfoFF);						
					}				
				}
				
			// if Partner already exist	
			} else if (partner.getPartnerId() > 0) {				
				partner.setUpdatedDate(new Date());			
				
				if (partnerDao.updatePartner(partner)){
					_tmpPartnerId = partner.getPartnerId();
				}	
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			 e.printStackTrace();
			throw e;
		}
		return _tmpPartnerId;
	}

	/**
	 * 
	 * @param partner
	 */
	public Partner searchByCode(Partner partner) throws Exception{
		Partner _tmpPartner = null;
		try {
			if (partner != null) {
				_tmpPartner = partnerDao.loadByCode(partner);
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			throw e;
		}
		return _tmpPartner;
	}
	
	/**
	 * 
	 * @param partner
	 */
	public List<Partner> searchPartner(Partner partner) throws Exception{
		List<Partner> partners = null;
		try {
			if (partner != null) {
				partners = partnerDao.searchPartner(partner);
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			throw e;
		}
		return partners;
	}
	
	
	public List<Partner> searchLightClient(Partner partner) throws Exception{
		List<Partner> partners = null;
		try {
			if (partner != null) {
				partners = partnerDao.searchLightClient(partner);
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			throw e;
		}
		return partners;
	}

	/**
	 * 
	 * @param partner
	 */
/*	public List<ShipPickUp> loadShipPickup(Partner partner)throws Exception{
		List<ShipPickUp> shipPickUps = null;
		try {
			if (partner != null) {
				shipPickUps = new ArrayList<ShipPickUp>();
				shipPickUps = partnerDao.loadShipPickup(partner);
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			throw e;
		}
		return shipPickUps;
	}*/

	/**
	 * 
	 * @param partner
	 */
	public List<CourierAccount> loadCourierAccount(Partner partner) throws Exception{
		List<CourierAccount> courierAccounts = null;
		try {
			if (partner != null) {
				courierAccounts = new ArrayList<CourierAccount>();
				courierAccounts = partnerDao.loadCourierAccounts(partner);
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			throw e;
		}
		return courierAccounts;
	}

	/**
	 * 
	 * @param partner
	 */
	public List<PartnerContact> loadContacts(Partner partner) throws Exception{
		List<PartnerContact> partnerContacts = null;
		try {
			if (partner != null) {
				partnerContacts = new ArrayList<PartnerContact>();
				partnerContacts = partnerDao.loadPartnerContacts(partner);
				//Load Contacts From EVERYONE
				if(partner.getDepartmentLotId() > 0){
					Partner auxPartner = new Partner();
					auxPartner.setPartnerId(partner.getPartnerId());
					auxPartner.setDepartmentLotId(Constants.MASTER_VALUE_DEPARTMENT_EVERYONE);				
					partnerContacts.addAll(partnerDao.loadPartnerContacts(auxPartner));
				}
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			throw e;
		}
		return partnerContacts;
	}

	/**
	 * 
	 * @param partner
	 */
	public PartnerDepartmentInfo loadDepartmentInfo(Partner partner) throws Exception{
		PartnerDepartmentInfo partnerDepartmentInfo = null;
		try {
			if (partner != null) {
				partnerDepartmentInfo = new PartnerDepartmentInfo();
				partnerDepartmentInfo = partnerDao.loadDeparmentInfo(partner);
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			throw e;
		}
		return partnerDepartmentInfo;
	}

	/**
	 * 
	 * @param partner
	 */
	public List<CallHistory> loadCallHistory(Partner partner) throws Exception{
		List<CallHistory> callHistory = null;
		try {
			if (partner != null) {
				callHistory = new ArrayList<CallHistory>();
				callHistory = partnerDao.loadCallHistory(partner);
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			throw e;
		}
		return callHistory;
	}
	
	public IPartnerDao getPartnerDao(){
		return partnerDao;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setPartnerDao(IPartnerDao newVal){
		partnerDao = newVal;
	}

	public IAddressLogic getAddressLogic() {
		return addressLogic;
	}

	public void setAddressLogic(IAddressLogic addressLogic) {
		this.addressLogic = addressLogic;
	}

	public IPhoneLogic getPhoneLogic() {
		return phoneLogic;
	}

	public void setPhoneLogic(IPhoneLogic phoneLogic) {
		this.phoneLogic = phoneLogic;
	}

	public IPartnerDepartmentInfoLogic getPartnerDepartmentInfoLogic() {
		return partnerDepartmentInfoLogic;
	}

	public void setPartnerDepartmentInfoLogic(
			IPartnerDepartmentInfoLogic partnerDepartmentInfoLogic) {
		this.partnerDepartmentInfoLogic = partnerDepartmentInfoLogic;
	}

	public List<ClientRatesPort> loadClientRates(ClientRatesPort clientRatesPort) throws Exception {
		List<ClientRatesPort> clientRatesPorts = null;
		if(clientRatesPort != null){
			try {
				clientRatesPorts = new ArrayList<ClientRatesPort>();
				clientRatesPorts = partnerDao.loadClientRatesPort(clientRatesPort);
				for(ClientRatesPort _tmpClientRatesPort : clientRatesPorts){
					ClientRate _tmpClientRate = new ClientRate();
					_tmpClientRate.setClientRatePortsId(_tmpClientRatesPort.getClientRatePortId());
					_tmpClientRatesPort.setClientRates(partnerDao.loadClientRates(_tmpClientRate));
				}
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				throw e;
			}
		}
		return clientRatesPorts;
	}

	public PartnerContact loadPartnerContactMainInfo(PartnerContact partnerContact) throws Exception{
		try {
			if(partnerContact != null){
				partnerContact = partnerDao.loadPartnerContactMainInfo(partnerContact);
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			throw e;
		}
		return partnerContact;
	}
}