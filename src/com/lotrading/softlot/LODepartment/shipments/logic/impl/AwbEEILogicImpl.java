package com.lotrading.softlot.LODepartment.shipments.logic.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.LODepartment.shipments.dao.IAwbEEIDao;
import com.lotrading.softlot.LODepartment.shipments.entity.AwbEEI;
import com.lotrading.softlot.LODepartment.shipments.logic.IAwbEEILogic;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 20-Ene-2012 08:00:00 AM
 */
public class AwbEEILogicImpl implements IAwbEEILogic{

	private IAwbEEIDao awbEEIDao;
	private Log log = LogFactory.getLog(AwbEEILogicImpl.class);
	
	public List<AwbEEI> saveAwbEEI(List<AwbEEI> awbEEIList) throws Exception {
		if(awbEEIList != null){
			try{
				awbEEIDao.saveAwbEEI(awbEEIList);
			}catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				e.printStackTrace();
				throw e;
			}
		}
		return awbEEIList;
	}

	public boolean deleteAwbEEI(AwbEEI awbEEI) throws Exception {
		boolean deleted = false;
		if(awbEEI != null){
			try {
				deleted = awbEEIDao.deleteAwbEEI(awbEEI);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				e.printStackTrace();
				throw e;
			}
		}
		return deleted;
	}

	public IAwbEEIDao getAwbEEIDao() {
		return awbEEIDao;
	}

	public void setAwbEEIDao(IAwbEEIDao awbEEIDao) {
		this.awbEEIDao = awbEEIDao;
	}

}
