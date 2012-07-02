package com.lotrading.softlot.invoice.dao;

import java.util.List;

import com.lotrading.softlot.invoice.entity.Invoice;
import com.lotrading.softlot.invoice.entity.PackingListItem;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 12-Mar-2012 11:15:00 a.m.
 */

public interface IPackingListItemDao {

	public List<PackingListItem> loadPackingListItem(Invoice invoice) throws Exception;
	
}
