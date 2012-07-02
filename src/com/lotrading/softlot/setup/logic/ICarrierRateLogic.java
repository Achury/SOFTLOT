package com.lotrading.softlot.setup.logic;

import com.lotrading.softlot.setup.entity.CarrierPorts;
import com.lotrading.softlot.setup.entity.CarrierRate;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 27-Sep-2011 12:00:00 AM
 */
public interface ICarrierRateLogic {

	/**
	 * 
	 * @param carrierRate
	 * @throws Exception 
	 */
	public CarrierPorts saveCarrierRate(CarrierPorts carrierPort) throws Exception;

	/**
	 * 
	 * @param carrierRate
	 */
	public boolean deleteCarrierRate(CarrierRate carrierRate) throws Exception;

}