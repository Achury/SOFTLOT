package com.lotrading.softlot.LODepartment.shipments.dao;

import java.util.List;

import com.lotrading.softlot.LODepartment.shipments.entity.BlCostSale;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 27-Sep-2011 12:00:00 AM
 */
public interface IBlCostSaleDao {

	/**
	 * 
	 * @param blcostSale
	 * @throws Exception 
	 */
	public int createBlCostSale(BlCostSale blcostSale) throws Exception;

	/**
	 * 
	 * @param blcostSale
	 */
	public boolean updateBlCostSale(BlCostSale blcostSale) throws Exception;
	
	/**
	 * 
	 * @param blcostSale
	 */
	public List<BlCostSale> saveBlCostSale(List<BlCostSale> blcostSale) throws Exception;

	/**
	 * 
	 * @param blcostSale
	 */
	public boolean deleteBlCostSale(BlCostSale blcostSale) throws Exception;

}