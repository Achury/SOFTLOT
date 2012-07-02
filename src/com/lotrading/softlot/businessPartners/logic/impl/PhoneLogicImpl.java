package com.lotrading.softlot.businessPartners.logic.impl;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.lotrading.softlot.businessPartners.dao.IPhoneDao;
import com.lotrading.softlot.businessPartners.entity.Phone;
import com.lotrading.softlot.businessPartners.logic.IPhoneLogic;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 11:00:01 a.m.
 */
public class PhoneLogicImpl implements IPhoneLogic {

	private Log log = LogFactory.getLog(PhoneLogicImpl.class);
	private IPhoneDao phoneDao;

	public PhoneLogicImpl() {

	}

	/**
	 * 
	 * @param phone
	 * @throws Exception 
	 */
	public int savePhone(Phone phone) throws Exception {
		int  _tmpPhoneId = 0;
		try {
			if (phone == null) {
				return _tmpPhoneId;
			}
			//if Phone is New
			if (phone.getPhoneId() <= 0) {
				phone.setEnteredDate(new Date());
				_tmpPhoneId = phoneDao.createPhone(phone);
				
			//if Phone already exist
			} else if(phone.getPhoneId() > 0){
				if (phoneDao.updatePhone(phone)){
					_tmpPhoneId = phone.getPhoneId();
				}	
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			throw e;
		}
		return _tmpPhoneId;
	}

	public IPhoneDao getPhoneDao() {
		return phoneDao;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setPhoneDao(IPhoneDao newVal) {
		phoneDao = newVal;
	}

}