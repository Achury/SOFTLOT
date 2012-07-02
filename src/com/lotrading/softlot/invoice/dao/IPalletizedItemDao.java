package com.lotrading.softlot.invoice.dao;


import java.util.List;

import com.lotrading.softlot.invoice.entity.Invoice;
import com.lotrading.softlot.invoice.entity.PalletizedItem;


/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 29-Feb-2012 04:15:55 p.m.
 */
public interface IPalletizedItemDao {

	public List<PalletizedItem> loadPalletizedItems(Invoice Invoice) throws Exception;
	
}
