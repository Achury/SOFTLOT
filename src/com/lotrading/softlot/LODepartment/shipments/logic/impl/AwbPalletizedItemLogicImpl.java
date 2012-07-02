package com.lotrading.softlot.LODepartment.shipments.logic.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.LODepartment.shipments.dao.IAwbPalletizedItemDao;
import com.lotrading.softlot.LODepartment.shipments.entity.AwbPalletizedItem;
import com.lotrading.softlot.LODepartment.shipments.logic.IAwbPalletizedItemLogic;

public class AwbPalletizedItemLogicImpl implements IAwbPalletizedItemLogic {

	private IAwbPalletizedItemDao awbPalletizedItemDao;
	private Log log = LogFactory.getLog(AwbPalletizedItemLogicImpl.class);
	
	/**
	 * 
	 * @param awbPalletizedItem
	 * @throws Exception 
	 */
	public List<AwbPalletizedItem> saveAwbPalletizedItems(List<AwbPalletizedItem> awbPalletizedItems) throws Exception {
		if(awbPalletizedItems != null){
			
			try {
				awbPalletizedItems =awbPalletizedItemDao.saveAwbPalletizedItems(awbPalletizedItems);				
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				e.printStackTrace();
				throw e;
			}
		}
		
		return awbPalletizedItems;
	}

	/**
	 * 
	 * @param awbPalletizedItem
	 * @throws Exception 
	 */
	public boolean deleteAwbPalletizedItem(AwbPalletizedItem awbPalletizedItem) throws Exception {
		boolean deleted = false;
		if(awbPalletizedItem != null){
			try {
				deleted = awbPalletizedItemDao.deleteAwbPalletizedItem(awbPalletizedItem);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				e.printStackTrace();
				throw e;
			}
		}
		return deleted;
	}

	public IAwbPalletizedItemDao getAwbPalletizedItemDao() {
		return awbPalletizedItemDao;
	}

	public void setAwbPalletizedItemDao(IAwbPalletizedItemDao awbPalletizedItemDao) {
		this.awbPalletizedItemDao = awbPalletizedItemDao;
	}

}
