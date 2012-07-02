package com.lotrading.softlot.LODepartment.shipments.logic.impl;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.LODepartment.shipments.dao.IBlPalletizedItemDao;
import com.lotrading.softlot.LODepartment.shipments.entity.BlPalletizedItem;
import com.lotrading.softlot.LODepartment.shipments.logic.IBlPalletizedItemLogic;

/**
 * @author MARLON PINEDA
 * @version 1.0
 * @created 19-June-2012 12:00:00 AM
 */
public class BlPalletizedItemLogicImpl implements IBlPalletizedItemLogic {

	private IBlPalletizedItemDao blPalletizedItemDao;
	private Log log = LogFactory.getLog(BlPalletizedItemLogicImpl.class);
	
	public BlPalletizedItemLogicImpl(){

	}

	/**
	 * 
	 * @param blPalletizedItem
	 * @throws Exception 
	 */
	public List<BlPalletizedItem> saveBlPalletizedItems(List<BlPalletizedItem> blPalletizedItems) throws Exception{
		if(blPalletizedItems != null){
		
			try {
				blPalletizedItems = blPalletizedItemDao.saveBlPalletizedItems(blPalletizedItems);				
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				e.printStackTrace();
				throw e;
			}
		}
		
		return blPalletizedItems;
	}

	/**
	 * 
	 * @param blPalletizedItem
	 * @throws Exception 
	 */
	public boolean deleteBlPalletizedItem(BlPalletizedItem blPalletizedItem) throws Exception{
		boolean deleted = false;
		if(blPalletizedItem != null){
			try {
				deleted = blPalletizedItemDao.deleteBlPalletizedItem(blPalletizedItem);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				e.printStackTrace();
				throw e;
			}
		}
		return deleted;
	}

	public IBlPalletizedItemDao getBlPalletizedItemDao() {
		return blPalletizedItemDao;
	}

	public void setBlPalletizedItemDao(IBlPalletizedItemDao blPalletizedItemDao) {
		this.blPalletizedItemDao = blPalletizedItemDao;
	}

}