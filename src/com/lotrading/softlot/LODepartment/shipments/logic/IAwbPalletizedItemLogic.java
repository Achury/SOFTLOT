package com.lotrading.softlot.LODepartment.shipments.logic;

import java.util.List;

import com.lotrading.softlot.LODepartment.shipments.entity.AwbPalletizedItem;


/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 20-Jun-2012 11:39:00 AM
 */
public interface IAwbPalletizedItemLogic {

	/**
	 * 
	 * @param awbPalletizedItems
	 * @throws Exception 
	 */
	public List<AwbPalletizedItem> saveAwbPalletizedItems(List<AwbPalletizedItem> awbPalletizedItems) throws Exception;

	/**
	 * 
	 * @param awbPalletizedItem
	 */
	public boolean deleteAwbPalletizedItem(AwbPalletizedItem awbPalletizedItem) throws Exception;
}
