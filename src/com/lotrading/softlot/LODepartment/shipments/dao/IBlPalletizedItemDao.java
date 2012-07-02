package com.lotrading.softlot.LODepartment.shipments.dao;
import java.util.List;

import com.lotrading.softlot.LODepartment.shipments.entity.BlPalletizedItem;

/**
 * @author MARLON PINEDA
 * @version 1.0
 * @created 19-June-2012 12:00:00 AM
 */
public interface IBlPalletizedItemDao {

	/**
	 * 
	 * @param blPalletizedItem
	 * @throws Exception 
	 */
	public int createBlPalletizedItem(BlPalletizedItem blPalletizedItem) throws Exception;

	/**
	 * 
	 * @param blPalletizedItem
	 */
	public boolean updateBlPalletizedItem(BlPalletizedItem blPalletizedItem) throws Exception;
	
	/**
	 * 
	 * @param List<BlPalletizedItem>
	 */
	public List<BlPalletizedItem> saveBlPalletizedItems(List<BlPalletizedItem> blPalletizedItems) throws Exception;

	/**
	 * 
	 * @param blPalletizedItem
	 */
	public boolean deleteBlPalletizedItem(BlPalletizedItem blPalletizedItem) throws Exception;

}