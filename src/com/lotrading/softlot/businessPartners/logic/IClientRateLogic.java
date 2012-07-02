package com.lotrading.softlot.businessPartners.logic;

import com.lotrading.softlot.businessPartners.entity.ClientRate;
import com.lotrading.softlot.businessPartners.entity.ClientRatesPort;

public interface IClientRateLogic {

	public ClientRatesPort saveClientRate(ClientRatesPort clientRatesPort) throws Exception;
	
	public boolean deleteClientRate(ClientRate clientRate) throws Exception;
	
	public ClientRate loadRateCost(ClientRatesPort clientRatesPort, ClientRate clientRate) throws Exception;
}
