package com.lotrading.softlot.setup.logic;
import java.util.List;

import com.lotrading.softlot.setup.entity.Carrier;
import com.lotrading.softlot.setup.entity.CarrierAwbNumber;
import com.lotrading.softlot.setup.entity.CarrierPorts;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:54 a.m.
 */
public interface IcarrierLogic {

	/**
	 * 
	 * @param carrier
	 * @throws Exception 
	 */
	public boolean saveCarrier(Carrier carrier) throws Exception;

	/**
	 * 
	 * @param carrier
	 * @throws Exception 
	 */
	public List<Carrier> searchCarrier(Carrier carrier) throws Exception;

	public List<CarrierPorts> loadCarrierRates(CarrierPorts carrierPort) throws Exception;
	
	public List<CarrierAwbNumber> loadCarrierAwbNumber(CarrierAwbNumber carrierAwbNumber) throws Exception;
}