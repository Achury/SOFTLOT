package com.lotrading.softlot.LODepartment.shipments.dao;

import java.util.List;

import com.lotrading.softlot.LODepartment.shipments.entity.BlInlandCS;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 27-Sep-2011 12:00:00 AM
 */
public interface IBlInlandCSDao {

	/**
	 * 
	 * @param blInlandCs
	 * @throws Exception 
	 */
	public int createBlInlandCS(BlInlandCS blInlandCs) throws Exception;

	/**
	 * 
	 * @param blInlandCs
	 */
	public boolean updateBlInlandCS(BlInlandCS blInlandCs) throws Exception;
	
	/**
	 * 
	 * @param blInlandCSItems
	 */
	public List<BlInlandCS> saveBlInlandCSItems(List<BlInlandCS> blInlandCSItems) throws Exception;

	/**
	 * 
	 * @param blInlandCs
	 */
	public boolean deleteBlInlandCS(BlInlandCS blInlandCs) throws Exception;

}