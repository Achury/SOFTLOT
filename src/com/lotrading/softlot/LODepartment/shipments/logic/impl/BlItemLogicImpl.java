package com.lotrading.softlot.LODepartment.shipments.logic.impl;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.LODepartment.shipments.dao.IBlItemDao;
import com.lotrading.softlot.LODepartment.shipments.entity.BlItem;
import com.lotrading.softlot.LODepartment.shipments.logic.IBlItemLogic;

/**
 * @author MARLON PINEDA
 * @version 1.0
 * @created 12-Jan-2012 12:00:00 AM
 */
public class BlItemLogicImpl implements IBlItemLogic {

	private IBlItemDao blItemDao;
	private Log log = LogFactory.getLog(BlItemLogicImpl.class);
	
	public BlItemLogicImpl(){

	}

	/**
	 * 
	 * @param blItem
	 * @throws Exception 
	 */
	public List<BlItem> saveBlItems(List<BlItem> blItems) throws Exception{
		if(blItems != null){
		
			try {
				blItems = blItemDao.saveBlItems(blItems);				
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				e.printStackTrace();
				throw e;
			}
		}
		
		return blItems;
	}

	/**
	 * 
	 * @param blItem
	 * @throws Exception 
	 */
	public boolean deleteBlItem(BlItem blItem) throws Exception{
		boolean deleted = false;
		if(blItem != null){
			try {
				deleted = blItemDao.deleteBlItem(blItem);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				e.printStackTrace();
				throw e;
			}
		}
		return deleted;
	}

	public IBlItemDao getBlItemDao() {
		return blItemDao;
	}

	public void setBlItemDao(IBlItemDao blItemDao) {
		this.blItemDao = blItemDao;
	}

}