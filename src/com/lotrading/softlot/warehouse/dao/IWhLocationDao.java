package com.lotrading.softlot.warehouse.dao;

import com.lotrading.softlot.warehouse.entity.WhLocation;

/**
 * @author MARLON PINEDA
 * @version 1.0
 * @created 20-June-2012 10:40:00 AM
 */
public interface IWhLocationDao {
	
	
	/**
	 * 
	 * @param whLocation
	 * @throws Exception 
	 */
	public java.util.List<WhLocation> searchWhLocation(WhLocation whLocation) throws Exception;
}
