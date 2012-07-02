package com.lotrading.softlot.warehouse.logic.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.warehouse.dao.IWhItemDao;
import com.lotrading.softlot.warehouse.entity.WhItem;
import com.lotrading.softlot.warehouse.logic.IWhItemLogic;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 23-Feb-2012 10:40:00 AM
 */
public class WhItemLogicImpl implements IWhItemLogic{
	
	private IWhItemDao whItemDao;
	private Log log = LogFactory.getLog(WhItemLogicImpl.class);
	
	public List<WhItem> saveWhItem(List<WhItem> whItemList) throws Exception{
		try {
			whItemDao.saveWhItem(whItemList);
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		}
		return whItemList;
	}

	public boolean updateWhItem(WhItem whItem) throws Exception {
		boolean updated = false;
		try {
			updated = whItemDao.updateWhItem(whItem);
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		}
		return updated;
	}

	public WhItem createWhItem(WhItem whItem) throws Exception {
		try {
			whItemDao.createWhItem(whItem);
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		}
		return whItem;
	}

	public WhItem loadWhItem(WhItem whItem) throws Exception {
		try {
			whItem = whItemDao.loadWhItem(whItem);
		} catch (Exception e) {
			log.error("An Exception has been thrown:" + e);
			throw e;
		}
		return whItem;
	}
	
	public IWhItemDao getWhItemDao() {
		return whItemDao;
	}

	public void setWhItemDao(IWhItemDao whItemDao) {
		this.whItemDao = whItemDao;
	}
	
}
