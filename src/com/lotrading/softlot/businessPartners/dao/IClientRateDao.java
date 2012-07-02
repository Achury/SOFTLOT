package com.lotrading.softlot.businessPartners.dao;

import com.lotrading.softlot.businessPartners.entity.ClientRate;
import com.lotrading.softlot.businessPartners.entity.ClientRatesPort;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 04-enero-2012 2:38:45 p.m.
 */

public interface IClientRateDao {

	public int createClientRate(ClientRatesPort clientRatePort) throws Exception;

	public boolean updateClientRate(ClientRatesPort clientRatePort) throws Exception;

	public boolean deleteClientRate(ClientRate clientRate) throws Exception;
	
	public ClientRate loadRateCost(ClientRatesPort clientRatesPort, ClientRate clientRate) throws Exception;
}
