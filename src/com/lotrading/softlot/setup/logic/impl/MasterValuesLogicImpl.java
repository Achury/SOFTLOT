package com.lotrading.softlot.setup.logic.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.lotrading.softlot.setup.dao.IMasterValuesDao;
import com.lotrading.softlot.setup.entity.MasterValue;
import com.lotrading.softlot.setup.logic.IMasterValueLogic;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:58 a.m.
 */
public class MasterValuesLogicImpl implements IMasterValueLogic {
	
	private Log log = LogFactory.getLog(MasterValuesLogicImpl.class);
	private IMasterValuesDao masterValueDao;
	
	public MasterValuesLogicImpl(){

	}

	/**
	 * 
	 * @param masterValue
	 */
	public boolean saveMasterValue(MasterValue masterValue) throws Exception{
		boolean _tmpReturn = false;
		try {
			if (masterValue == null)
				return false;
			if (masterValue.getValueId()<= 0) {				
				masterValue.setEnteredDate(new Date());
				_tmpReturn = masterValueDao.createMasterValue(masterValue);
			} else if (masterValue.getValueId() > 0) {
				_tmpReturn = masterValueDao.updateMasterValue(masterValue);
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			throw e;
		}
		return _tmpReturn;
	}

	/**
	 * 
	 * @param masterValue
	 */
	public List<MasterValue> searchMasterValue(MasterValue masterValue) throws Exception{
		List<MasterValue> masterValues = null;
		try {
			if (masterValue != null) {
				masterValues = new ArrayList<MasterValue>();
				masterValues = masterValueDao.searchMasterValue(masterValue);
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			throw e;
		}
		return masterValues;
	}
	public IMasterValuesDao getMasterValueDao() {
		return masterValueDao;
	}

	public void setMasterValueDao(IMasterValuesDao masterValueDao) {
		this.masterValueDao = masterValueDao;
	}


}