package com.lotrading.softlot.LODepartment.shipments.logic;

import java.util.List;

import com.lotrading.softlot.LODepartment.shipments.entity.AwbItem;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 20-Ene-2012 08:00:00 AM
 */
public interface IAwbItemsLogic {

	/**
	 * 
	 * @param awbItem
	 * @throws Exception 
	 */
	public List<AwbItem> saveAwbItems(List<AwbItem> awbItems) throws Exception;

	/**
	 * 
	 * @param awbItem
	 */
	public boolean deleteAwbItem(AwbItem awbItem) throws Exception;

	
}