package com.lotrading.softlot.LODepartment.shipments.logic;

import java.util.List;

import com.lotrading.softlot.LODepartment.shipments.entity.BlPalletizedItem;

/**
 * @author MARLON PINEDA
 * @version 1.0
 * @created 19-June-2012 12:00:00 AM
 */
public interface IBlPalletizedItemLogic {

	/**
	 * 
	 * @param blPalletizedItems
	 * @throws Exception 
	 */
	public List<BlPalletizedItem> saveBlPalletizedItems(List<BlPalletizedItem> blPalletizedItems) throws Exception;

	/**
	 * 
	 * @param blPalletizedItem
	 */
	public boolean deleteBlPalletizedItem(BlPalletizedItem blPalletizedItem) throws Exception;

}