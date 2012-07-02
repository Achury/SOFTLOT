package com.lotrading.softlot.setup.logic.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.setup.dao.ICarrierDao;
import com.lotrading.softlot.setup.entity.Carrier;
import com.lotrading.softlot.setup.entity.CarrierAwbNumber;
import com.lotrading.softlot.setup.entity.CarrierPorts;
import com.lotrading.softlot.setup.entity.CarrierRate;
import com.lotrading.softlot.setup.logic.IcarrierLogic;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:51 a.m.
 */
public class CarrierLogicImpl implements IcarrierLogic {

	private Log log = LogFactory.getLog(CarrierLogicImpl.class);
	private ICarrierDao carrierDao;

	public CarrierLogicImpl(){

	}

	/**
	 * 
	 * @param carrier
	 * @throws Exception 
	 */
	public boolean saveCarrier(Carrier carrier) throws Exception{
		boolean _tmpReturn = false;
		try {
			if (carrier == null)
				return false;
			if (carrier.getCarrierId() <= 0) {
				carrier.setEnteredDate(new Date());    
				if(carrierDao.createCarrier(carrier) > 0){
					_tmpReturn = true;
				}
			} else if (carrier.getCarrierId() > 0) {
				_tmpReturn = carrierDao.updateCarrier(carrier);
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			throw e;
		}
		return _tmpReturn;
	}

	/**
	 * 
	 * @param carrier
	 * @throws Exception 
	 */
	public List<Carrier> searchCarrier(Carrier carrier) throws Exception{
		List<Carrier> carriers = null;
		try {
			if(carrier != null){
				carriers = new ArrayList<Carrier>();
				carriers = carrierDao.searchCarrier(carrier);
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			throw e;
		}
		return carriers;
	}

	public List<CarrierPorts> loadCarrierRates(CarrierPorts carrierPort) throws Exception {
		List<CarrierPorts> carrierPorts = null;
		try {
			if(carrierPort != null){
				carrierPorts = new ArrayList<CarrierPorts>();
				carrierPorts = carrierDao.loadCarrierPorts(carrierPort);
				for(CarrierPorts _tmpCarrierPort : carrierPorts){
					CarrierRate _tmpCarrierRate = new CarrierRate();
					_tmpCarrierRate.setCarrierPortId(_tmpCarrierPort.getCarrierPortId());
					_tmpCarrierPort.setCarrierRates(carrierDao.loadCarrierRates(_tmpCarrierRate));
				}
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			throw e;
		}
		return carrierPorts;
	}

	public List<CarrierAwbNumber> loadCarrierAwbNumber(CarrierAwbNumber carrierAwbNumber) throws Exception {
		List<CarrierAwbNumber> carrierAwbNumbers = null;
		try {
			if(carrierAwbNumber != null){
				carrierAwbNumbers = carrierDao.loadCarrierAwbNumber(carrierAwbNumber);
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			throw e;
		}
		return carrierAwbNumbers;
	}

	public ICarrierDao getCarrierDao(){
		return carrierDao;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setCarrierDao(ICarrierDao newVal){
		carrierDao = newVal;
	}
	
}