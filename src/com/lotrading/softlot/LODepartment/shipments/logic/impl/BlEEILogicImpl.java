package com.lotrading.softlot.LODepartment.shipments.logic.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.LODepartment.shipments.dao.IBlEEIDao;
import com.lotrading.softlot.LODepartment.shipments.entity.BlEEI;
import com.lotrading.softlot.LODepartment.shipments.logic.IBlEEILogic;

/**
 * @author MARLON PINEDA
 * @version 1.0
 * @created 25-Apr-2012 08:00:00 AM
 */
public class BlEEILogicImpl implements IBlEEILogic{

	private IBlEEIDao blEEIDao;
	private Log log = LogFactory.getLog(BlEEILogicImpl.class);
	
	public List<BlEEI> saveBlEEI(List<BlEEI> blEEIList) throws Exception {
		if(blEEIList != null){
			try{
				blEEIDao.saveBlEEI(blEEIList);
			}catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				e.printStackTrace();
				throw e;
			}
		}
		return blEEIList;
	}

	public boolean deleteBlEEI(BlEEI blEEI) throws Exception {
		boolean deleted = false;
		if(blEEI != null){
			try {
				deleted = blEEIDao.deleteBlEEI(blEEI);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				e.printStackTrace();
				throw e;
			}
		}
		return deleted;
	}
	
	public IBlEEIDao getBlEEIDao() {
		return blEEIDao;
	}

	public void setBlEEIDao(IBlEEIDao blEEIDao) {
		this.blEEIDao = blEEIDao;
	}

}
