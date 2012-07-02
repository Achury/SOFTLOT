package com.lotrading.softlot.setup.logic.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.setup.dao.ICarrierAwbNumberDao;
import com.lotrading.softlot.setup.entity.CarrierAwbNumber;
import com.lotrading.softlot.setup.logic.ICarrierAwbNumberLogic;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 27-Sep-2011 12:00:00 AM
 */
public class CarrierAwbNumberLogicImpl implements ICarrierAwbNumberLogic {

	private ICarrierAwbNumberDao carrierAwbNumberDao;
	private Log log = LogFactory.getLog(CarrierAwbNumberLogicImpl.class);
	
	public CarrierAwbNumberLogicImpl(){

	}

	/**
	 * 
	 * @param carrierAwbNumber
	 * @throws Exception 
	 */
	public List<CarrierAwbNumber> saveCarrierAwbNumber(List<CarrierAwbNumber> carrierAwbNumbers) throws Exception{
		if(carrierAwbNumbers != null){
			if(!carrierAwbNumbers.isEmpty()){
				try {
					carrierAwbNumbers = carrierAwbNumberDao.createCarrierAwbNumber(carrierAwbNumbers);			
				} catch (Exception e) {
					log.error("An Exception has been thrown " + e);
					e.printStackTrace();
					throw e;
				}
			}
		}
		return carrierAwbNumbers;
	}
	
	public boolean updateCarrierAwbBlNumber(CarrierAwbNumber carrierAwbNumber) throws Exception{
		boolean _tmpReturn = false;
		if(carrierAwbNumber != null){
			try {
				_tmpReturn = carrierAwbNumberDao.updateCarrierAwbNumber(carrierAwbNumber);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				e.printStackTrace();
				throw e;
			}
		}
		return _tmpReturn;
	}

	/**
	 * 
	 * @param carrierAwbNumber
	 * @throws Exception 
	 */
	public boolean deleteCarrierAwbNumber(CarrierAwbNumber carrierAwbNumber) throws Exception{
		boolean deleted = false;
		if(carrierAwbNumber != null){
			try {
				deleted = carrierAwbNumberDao.deleteCarrierAwbNumber(carrierAwbNumber);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				e.printStackTrace();
				throw e;
			}
		}
		return deleted;
	}

	public ICarrierAwbNumberDao getCarrierAwbNumberDao() {
		return carrierAwbNumberDao;
	}

	public void setCarrierAwbNumberDao(ICarrierAwbNumberDao carrierAwbNumberDao) {
		this.carrierAwbNumberDao = carrierAwbNumberDao;
	}

}