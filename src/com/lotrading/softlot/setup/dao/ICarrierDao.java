package com.lotrading.softlot.setup.dao;
import java.util.List;

import com.lotrading.softlot.setup.entity.Carrier;
import com.lotrading.softlot.setup.entity.CarrierAwbNumber;
import com.lotrading.softlot.setup.entity.CarrierPorts;
import com.lotrading.softlot.setup.entity.CarrierRate;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:54 a.m.
 */
public interface ICarrierDao {

	/**
	 * 
	 * @param carrier
	 * @throws Exception 
	 */
	public int createCarrier(Carrier carrier) throws Exception;

	/**
	 * 
	 * @param carrier
	 * @throws Exception 
	 */
	public boolean updateCarrier(Carrier carrier) throws Exception;

	/**
	 * 
	 * @param carrier
	 * @throws Exception 
	 */
	public List<Carrier> searchCarrier(Carrier carrier) throws Exception;

	/**
	 * 
	 * @param carrier
	 * @throws Exception 
	 */
	public List<CarrierPorts> loadCarrierPorts(CarrierPorts carrierPort) throws Exception;
	
	/**
	 * 
	 * @param carrier
	 * @throws Exception 
	 */
	public List<CarrierRate> loadCarrierRates(CarrierRate carrierRate) throws Exception;
	
	/**
	 * 
	 * @param carrier
	 * @throws Exception 
	 */
	public List<CarrierAwbNumber> loadCarrierAwbNumber(CarrierAwbNumber carrierAwbNumber) throws Exception;
	
}