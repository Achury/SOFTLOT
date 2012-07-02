package com.lotrading.softlot.LODepartment.shipments.dao;
import java.util.List;

import com.lotrading.softlot.LODepartment.shipments.entity.BlItem;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 27-Sep-2011 12:00:00 AM
 */
public interface IBlItemDao {

	/**
	 * 
	 * @param blItem
	 * @throws Exception 
	 */
	public int createBlItem(BlItem blItem) throws Exception;

	/**
	 * 
	 * @param blItem
	 */
	public boolean updateBlItem(BlItem blItem) throws Exception;
	
	/**
	 * 
	 * @param List<BlItem>
	 */
	public List<BlItem> saveBlItems(List<BlItem> blItems) throws Exception;

	/**
	 * 
	 * @param blItem
	 */
	public boolean deleteBlItem(BlItem blItem) throws Exception;

}