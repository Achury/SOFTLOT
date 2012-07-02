package com.lotrading.softlot.invoice.logic.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lotrading.softlot.invoice.dao.IPackingListItemDao;
import com.lotrading.softlot.invoice.entity.Invoice;
import com.lotrading.softlot.invoice.entity.PackingListItem;
import com.lotrading.softlot.invoice.logic.IPackingListItemLogic;

public class PackingListItemLogicImpl implements IPackingListItemLogic {

	private Log log = LogFactory.getLog(PackingListItemLogicImpl.class);
	private IPackingListItemDao packingListItemDao;
	
	public List<PackingListItem> loadPackingListItems(Invoice invoice) throws Exception {
		List<PackingListItem> itemList = null;
		try {
			if (invoice != null){	
				itemList = packingListItemDao.loadPackingListItem(invoice);
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			throw e;
		}
		return itemList;
	}

	public IPackingListItemDao getPackingListItemDao() {
		return packingListItemDao;
	}

	public void setPackingListItemDao(IPackingListItemDao packingListItemDao) {
		this.packingListItemDao = packingListItemDao;
	}

}
