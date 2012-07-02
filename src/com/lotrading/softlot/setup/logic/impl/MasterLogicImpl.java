package com.lotrading.softlot.setup.logic.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.setup.dao.IMasterDao;
import com.lotrading.softlot.setup.entity.Master;
import com.lotrading.softlot.setup.logic.IMasterLogic;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:57 a.m.
 */
public class MasterLogicImpl implements IMasterLogic {

	private Log log = LogFactory.getLog(MasterLogicImpl.class);
	private IMasterDao masterDao;

	public MasterLogicImpl() {

	}

	/**
	 * 
	 * @param master
	 * @throws Exception
	 */
	public boolean saveMaster(Master master) throws Exception {
		boolean _tmpReturn = false;
		try {
			if (master == null) {
				return false;
			}
			if (master.getMasterId() <= 0) {
				_tmpReturn = masterDao.createMaster(master);
			} else if (master.getMasterId() > 0) {
				_tmpReturn = masterDao.updateMaster(master);
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			throw e;
		}
		return _tmpReturn;
	}

	/**
	 * 
	 * @param master
	 * @throws Exception 
	 */
	public List<Master> searchMaster(Master master) throws Exception {
		List<Master> masters = null;
		try {
			if(master != null){
				masters = new ArrayList<Master>();
				masters = masterDao.searchMaster(master);
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			throw e;
		}
		return masters;
	}
	
	public IMasterDao getMasterDao() {
		return masterDao;
	}

	public void setMasterDao(IMasterDao masterDao) {
		this.masterDao = masterDao;
	}

}