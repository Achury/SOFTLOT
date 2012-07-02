package com.lotrading.softlot.businessPartners.logic.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.businessPartners.dao.IClientRateDao;
import com.lotrading.softlot.businessPartners.entity.ClientRate;
import com.lotrading.softlot.businessPartners.entity.ClientRatesPort;
import com.lotrading.softlot.businessPartners.logic.IClientRateLogic;
import com.lotrading.softlot.util.base.dao.DAOTemplate;

public class ClientRateLogicImpl extends DAOTemplate implements IClientRateLogic {

	private Log log = LogFactory.getLog(ClientRateLogicImpl.class);
	private IClientRateDao clientRateDao;
	
	public ClientRatesPort saveClientRate(ClientRatesPort clientRatesPort) throws Exception {
		if(clientRatesPort != null){
			try {
				if(clientRatesPort.getClientRatePortId() <= 0){
					clientRatesPort.setClientRatePortId(clientRateDao.createClientRate(clientRatesPort));
				}else{
					clientRateDao.updateClientRate(clientRatesPort); 
				}
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				throw e;
			}
		}
		return clientRatesPort;
	}

	public boolean deleteClientRate(ClientRate clientRate) throws Exception {
		boolean deleted = false;
		if(clientRate != null){
			try {
				deleted = clientRateDao.deleteClientRate(clientRate);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				throw e;
			}
		}
		return deleted;
	}

	public IClientRateDao getClientRateDao() {
		return clientRateDao;
	}

	public void setClientRateDao(IClientRateDao clientRateDao) {
		this.clientRateDao = clientRateDao;
	}

	public ClientRate loadRateCost(ClientRatesPort clientRatesPort, ClientRate clientRate) throws Exception{
		if(clientRatesPort != null && clientRate != null){
			try {
				clientRate = clientRateDao.loadRateCost(clientRatesPort, clientRate);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				throw e;
			}
		}
		return clientRate;
	}
}
