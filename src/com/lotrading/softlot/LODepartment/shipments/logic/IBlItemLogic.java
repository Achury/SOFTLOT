package com.lotrading.softlot.LODepartment.shipments.logic;

import java.util.List;

import com.lotrading.softlot.LODepartment.shipments.entity.BlItem;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 27-Sep-2011 12:00:00 AM
 */
public interface IBlItemLogic {

	/**
	 * 
	 * @param blItem
	 * @throws Exception 
	 */
	public List<BlItem> saveBlItems(List<BlItem> blItems) throws Exception;

	/**
	 * 
	 * @param blItem
	 */
	public boolean deleteBlItem(BlItem blItem) throws Exception;

}