package com.lotrading.softlot.LODepartment.shipments.logic.impl;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.LODepartment.shipments.dao.IAwbItemsDao;
import com.lotrading.softlot.LODepartment.shipments.entity.AwbItem;
import com.lotrading.softlot.LODepartment.shipments.logic.IAwbItemsLogic;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 20-Ene-2012 08:00:00 AM
 */
public class AwbItemsLogicImpl implements IAwbItemsLogic {

	private IAwbItemsDao awbItemsDao;
	private Log log = LogFactory.getLog(AwbItemsLogicImpl.class);
	
	public AwbItemsLogicImpl(){

	}

	/**
	 * 
	 * @param awbItem
	 * @throws Exception 
	 */
	public List<AwbItem> saveAwbItems(List<AwbItem> awbItems) throws Exception{
		if(awbItems != null){
			try {
				awbItems = awbItemsDao.saveAwbItems(awbItems);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				e.printStackTrace();
				throw e;
			}
		}
		return awbItems;
	}

	/**
	 * 
	 * @param awbItem
	 * @throws Exception 
	 */
	public boolean deleteAwbItem(AwbItem awbItem) throws Exception{
		boolean deleted = false;
		if(awbItem != null){
			try {
				deleted = awbItemsDao.deleteAwbItem(awbItem);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				e.printStackTrace();
				throw e;
			}
		}
		return deleted;
	}


	public IAwbItemsDao getAwbItemsDao() {
		return awbItemsDao;
	}

	public void setAwbItemsDao(IAwbItemsDao awbItemsDao) {
		this.awbItemsDao = awbItemsDao;
	}
}