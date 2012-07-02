package com.lotrading.softlot.LODepartment.shipments.dao;

import java.util.List;

import com.lotrading.softlot.LODepartment.shipments.entity.AwbPalletizedItem;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 20-Jun-2012 11:29:00 AM
 */
public interface IAwbPalletizedItemDao {

	/**
	 * 
	 * @param awbPalletizedItem
	 * @throws Exception 
	 */
	public int creatAwbPalletizedItem(AwbPalletizedItem awbPalletizedItem) throws Exception;

	/**
	 * 
	 * @param awbPalletizedItem
	 */
	public boolean updateAwbPalletizedItem(AwbPalletizedItem awbPalletizedItem) throws Exception;
	
	/**
	 * 
	 * @param List<AwbPalletizedItem>
	 */
	public List<AwbPalletizedItem> saveAwbPalletizedItems(List<AwbPalletizedItem> awbPalletizedItems) throws Exception;

	/**
	 * 
	 * @param awbPalletizedItem
	 */
	public boolean deleteAwbPalletizedItem(AwbPalletizedItem awbPalletizedItem) throws Exception;
	
}
