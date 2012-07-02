package com.lotrading.softlot.warehouse.logic.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.warehouse.dao.IWhLocationDao;
import com.lotrading.softlot.warehouse.entity.WhLocation;
import com.lotrading.softlot.warehouse.logic.IWhLocationLogic;

/**
 * @author MARLON PINEDA
 * @version 1.0
 * @created 20-June-2012 10:40:00 AM
 */
public class WhLocationLogicImpl implements IWhLocationLogic{
	
	private IWhLocationDao whLocationDao;
	private Log log = LogFactory.getLog(WhLocationLogicImpl.class);
	
	@Override
	public List<WhLocation> searchWhLocation(WhLocation whLocation) throws Exception {
		List<WhLocation> whLocations = null;
		try {
			if(whLocation != null){
				whLocations = new ArrayList<WhLocation>();
				whLocations = whLocationDao.searchWhLocation(whLocation);
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			throw e;
		}
		return whLocations;
	}

	public IWhLocationDao getWhLocationDao() {
		return whLocationDao;
	}

	public void setWhLocationDao(IWhLocationDao whLocationDao) {
		this.whLocationDao = whLocationDao;
	}
	
	
	
}
