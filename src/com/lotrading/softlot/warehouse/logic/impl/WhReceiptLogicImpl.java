package com.lotrading.softlot.warehouse.logic.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.warehouse.dao.IWhReceiptDao;
import com.lotrading.softlot.warehouse.entity.WhItem;
import com.lotrading.softlot.warehouse.entity.WhReceipt;
import com.lotrading.softlot.warehouse.logic.IWhReceiptLogic;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 23-Feb-2012 10:40:00 AM
 */
public class WhReceiptLogicImpl implements IWhReceiptLogic{

	private IWhReceiptDao whReceiptDao;
	private Log log = LogFactory.getLog(WhReceiptLogicImpl.class);
	
	public List<WhReceipt> searchWhReceipt(WhReceipt whReceipt) throws Exception {
		return null;
	}

	public WhReceipt loadWhReceipt(WhReceipt whReceipt) {
		if(whReceipt != null){
			try {
				whReceipt = whReceiptDao.loadWhItems(whReceipt);
			} catch (Exception e) {
				log.error("An Exception has been thrown " + e);
				e.printStackTrace();
			}
		}
		return whReceipt;
	}

	public IWhReceiptDao getWhReceiptDao() {
		return whReceiptDao;
	}

	public void setWhReceiptDao(IWhReceiptDao whReceiptDao) {
		this.whReceiptDao = whReceiptDao;
	}
}
