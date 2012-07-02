package com.lotrading.softlot.setup.logic.impl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.setup.dao.ICarrierRateDao;
import com.lotrading.softlot.setup.entity.CarrierPorts;
import com.lotrading.softlot.setup.entity.CarrierRate;
import com.lotrading.softlot.setup.logic.ICarrierRateLogic;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 27-Sep-2011 12:00:00 AM
 */
public class CarrierRateLogicImpl implements ICarrierRateLogic {

	private ICarrierRateDao carrierRateDao;
	private Log log = LogFactory.getLog(CarrierRateLogicImpl.class);
	
	public CarrierRateLogicImpl(){

	}

	/**
	 * 
	 * @param carrierRate
	 * @throws Exception 
	 */
	public CarrierPorts saveCarrierRate(CarrierPorts carrierPort) throws Exception{
		if(carrierPort != null){
			try {
				if(carrierPort.getCarrierPortId() <= 0){
					carrierPort.setCarrierPortId(carrierRateDao.createCarrierRate(carrierPort));
				}else{
					carrierRateDao.updateCarrierRate(carrierPort);
				}
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				e.printStackTrace();
				throw e;
			}
		}
		return carrierPort;
	}

	/**
	 * 
	 * @param carrierRate
	 * @throws Exception 
	 */
	public boolean deleteCarrierRate(CarrierRate carrierRate) throws Exception{
		boolean deleted = false;
		if(carrierRate != null){
			try {
				deleted = carrierRateDao.deleteCarrierRate(carrierRate);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				e.printStackTrace();
				throw e;
			}
		}
		return deleted;
	}

	public ICarrierRateDao getCarrierRateDao() {
		return carrierRateDao;
	}

	public void setCarrierRateDao(ICarrierRateDao carrierRateDao) {
		this.carrierRateDao = carrierRateDao;
	}

}