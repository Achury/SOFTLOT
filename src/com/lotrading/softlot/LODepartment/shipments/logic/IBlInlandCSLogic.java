package com.lotrading.softlot.LODepartment.shipments.logic;

import java.util.List;

import com.lotrading.softlot.LODepartment.shipments.entity.BlInlandCS;
import com.lotrading.softlot.LODepartment.shipments.entity.BlItem;

/**
 * @author MARLON PINEDA
 * @version 1.0
 * @created 20-March-2012 12:00:00 AM
 */
public interface IBlInlandCSLogic {

	/**
	 * 
	 * @param blInlandCS
	 * @throws Exception 
	 */
	public int saveBlInlandCS(BlInlandCS blInlandCS) throws Exception;

	/**
	 * 
	 * @param blInlandCSItems
	 * @throws Exception 
	 */
	public List<BlInlandCS> saveBlInlandCSItems(List<BlInlandCS> blInlandCSItems) throws Exception;
	
	/**
	 * 
	 * @param blInlandCS
	 */
	public boolean deleteBlInlandCS(BlInlandCS blInlandCS) throws Exception;
	
	

}