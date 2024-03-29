package com.lotrading.softlot.warehouse.logic;

import java.util.List;

import com.lotrading.softlot.warehouse.entity.WhItem;
import com.lotrading.softlot.warehouse.entity.WhReceipt;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 23-Feb-2012 10:40:00 AM
 */
public interface IWhReceiptLogic {
	
	/**
	 * 
	 * @param whReceipt
	 * @throws Exception 
	 */
	public List<WhReceipt> searchWhReceipt(WhReceipt whReceipt) throws Exception;
	
	/**
	 * 
	 * @param whItem
	 * @throws Exception 
	 */
	public WhReceipt loadWhReceipt(WhReceipt whReceipt) throws Exception;
}
