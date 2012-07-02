package com.lotrading.softlot.invoice.logic.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.invoice.dao.IPalletizedItemDao;
import com.lotrading.softlot.invoice.entity.Invoice;
import com.lotrading.softlot.invoice.entity.PalletizedItem;
import com.lotrading.softlot.invoice.logic.IPalletizedItemLogic;

public class PalletizedItemLogicImpl implements IPalletizedItemLogic {

	private Log log = LogFactory.getLog(PalletizedItemLogicImpl.class);	
	private IPalletizedItemDao palletizedItemDao;
	
	public List<PalletizedItem> loadPalletizedItems(Invoice invoice) throws Exception {
		List<PalletizedItem> itemList = null;
		try {
			if (invoice != null){	
				itemList = palletizedItemDao.loadPalletizedItems(invoice);
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			throw e;
		}
		return itemList;
	}

	public IPalletizedItemDao getPalletizedItemDao() {
		return palletizedItemDao;
	}

	public void setPalletizedItemDao(IPalletizedItemDao palletizedItemDao) {
		this.palletizedItemDao = palletizedItemDao;
	}

}
