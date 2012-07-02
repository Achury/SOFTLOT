package com.lotrading.softlot.LODepartment.shipments.logic.impl;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.LODepartment.shipments.dao.IAwbInlandCsDao;
import com.lotrading.softlot.LODepartment.shipments.entity.AwbInlandCS;
import com.lotrading.softlot.LODepartment.shipments.logic.IAwbInlandCsLogic;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 20-Ene-2012 08:00:00 AM
 */
public class AwbInlandCsLogicImpl implements IAwbInlandCsLogic {

	private IAwbInlandCsDao awbInlandCsDao;
	private Log log = LogFactory.getLog(AwbInlandCsLogicImpl.class);
	
	public AwbInlandCsLogicImpl(){
		
	}

	/**
	 * 
	 * @param awbInlandCs
	 * @throws Exception 
	 */
	public List<AwbInlandCS> saveAwbInlandCsItems(List<AwbInlandCS> awbInlandCsList) throws Exception{
		if (awbInlandCsList != null) {
			try {
				awbInlandCsList = awbInlandCsDao.saveAwbInlandCSItems(awbInlandCsList);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				e.printStackTrace();
				throw e;
			}
		}
		return awbInlandCsList;
	}

	/**
	 * 
	 * @param awbInlandCs
	 * @throws Exception 
	 */
	public boolean deleteAwbInlandCs(AwbInlandCS awbInlandCs) throws Exception{
		boolean deleted = false;
		if(awbInlandCs != null){
			try {
				deleted = awbInlandCsDao.deleteAwbInlandCs(awbInlandCs);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				e.printStackTrace();
				throw e;
			}
		}
		return deleted;
	}

	public IAwbInlandCsDao getAwbInlandCsDao() {
		return awbInlandCsDao;
	}

	public void setAwbInlandCsDao(IAwbInlandCsDao awbInlandCsDao) {
		this.awbInlandCsDao = awbInlandCsDao;
	}
}