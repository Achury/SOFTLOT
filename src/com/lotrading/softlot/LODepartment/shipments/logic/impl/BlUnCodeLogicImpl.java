package com.lotrading.softlot.LODepartment.shipments.logic.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.LODepartment.shipments.dao.IBlUnCodeDao;
import com.lotrading.softlot.LODepartment.shipments.entity.BlUnCode;
import com.lotrading.softlot.LODepartment.shipments.logic.IBlUnCodeLogic;

/**
 * @author Marlon Pineda
 * @version 1.0
 * @created 12-Jan-2012 12:00:00 AM
 */
public class BlUnCodeLogicImpl implements IBlUnCodeLogic {
	private IBlUnCodeDao blUnCodeDao;
	private Log log = LogFactory.getLog(BlUnCodeLogicImpl.class);
	public BlUnCodeLogicImpl(){

	}
	
	/**
	 * 
	 * @param blUnCode
	 * @throws Exception 
	 */
	public List<BlUnCode> saveUnCode(List<BlUnCode> blUnCodes) throws Exception{
		if(blUnCodes != null){
			try {
				blUnCodeDao.saveUnCodes(blUnCodes);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				e.printStackTrace();
				throw e;
			}
		}
		return blUnCodes;
	}


	/**
	 * 
	 * @param blUnCode
	 */
	public boolean deleteUnCode(BlUnCode blUnCode) throws Exception{
		boolean deleted = false;
		if(blUnCode != null){
			try {
				deleted = blUnCodeDao.deleteUnCode(blUnCode);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				e.printStackTrace();
				throw e;
			}
		}
		return deleted;
	}

	public IBlUnCodeDao getBlUnCodeDao() {
		return blUnCodeDao;
	}

	public void setBlUnCodeDao(IBlUnCodeDao blUnCodeDao) {
		this.blUnCodeDao = blUnCodeDao;
	}

}