package com.lotrading.softlot.businessPartners.logic.impl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.businessPartners.dao.ICourierAccountDao;
import com.lotrading.softlot.businessPartners.entity.CourierAccount;
import com.lotrading.softlot.businessPartners.logic.ICourierAccountLogic;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:53 a.m.
 */
public class CourierAccountLogicImpl implements ICourierAccountLogic {

	private Log log = LogFactory.getLog(CourierAccountLogicImpl.class);
	private ICourierAccountDao courrierAccountDao;

	public CourierAccountLogicImpl(){

	}
	
	/**
	 * 
	 * @param courierAccount
	 * @throws Exception 
	 */
	public int saveCourierAccount(CourierAccount courierAccount) throws Exception{
		int _tmpCourierID = 0;
		try {
			if (courierAccount == null) {
				return 0;
			}
			if(courierAccount.getCourierAccountId() <= 0){
				_tmpCourierID = courrierAccountDao.createCourierAccount(courierAccount);
			}else if(courierAccount.getCourierAccountId() > 0){
				if (courrierAccountDao.updateCourierAccount(courierAccount)){
					_tmpCourierID = courierAccount.getCourierAccountId();
				}
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			throw e;
		}
		return _tmpCourierID;
	}
	
	public boolean deleteCourierAccount(CourierAccount courierAccount) throws Exception{
		boolean _tmpDeleted = false;
		try {
			if (courierAccount == null) {
				return _tmpDeleted;
			}
			if(courierAccount.getCourierAccountId() > 0){
				_tmpDeleted = courrierAccountDao.deleteCourierAccount(courierAccount);
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			throw e;
		}
		return _tmpDeleted;
	}

	public ICourierAccountDao getCourierAccountDao(){
		return courrierAccountDao;
	}
	
	public void setCourierAccountDao(ICourierAccountDao newVal){
		courrierAccountDao = newVal;
	}

}