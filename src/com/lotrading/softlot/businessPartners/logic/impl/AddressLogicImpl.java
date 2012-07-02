package com.lotrading.softlot.businessPartners.logic.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.businessPartners.dao.IAddressDao;
import com.lotrading.softlot.businessPartners.entity.Address;
import com.lotrading.softlot.businessPartners.logic.IAddressLogic;


/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:50 a.m.
 */
public class AddressLogicImpl implements IAddressLogic {

	private Log log = LogFactory.getLog(AddressLogicImpl.class);
	private IAddressDao addressDao;

	public AddressLogicImpl() {

	}

	/**
	 * 
	 * @param address
	 * @throws Exception 
	 */
	public int saveAddress(Address address) throws Exception {
		int _tmpAddressId = 0;		
		
		try {
			if (address == null) {
				return _tmpAddressId;
			}			
			
			//if Address is New
			if(address.getAddressId() <= 0){
				_tmpAddressId = addressDao.createAddress(address);
				
			//if Address already exist
			}else if(address.getAddressId() > 0){
				if (addressDao.updateAddress(address)){
					_tmpAddressId = address.getAddressId();
				}
			}
			
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			throw e;
		}
		return _tmpAddressId;
	}

	public IAddressDao getAddressDao() {
		return addressDao;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setAddressDao(IAddressDao newVal) {
		addressDao = newVal;
	}

}