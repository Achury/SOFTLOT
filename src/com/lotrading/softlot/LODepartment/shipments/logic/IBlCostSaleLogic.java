package com.lotrading.softlot.LODepartment.shipments.logic;

import java.util.List;

import com.lotrading.softlot.LODepartment.shipments.entity.BlCostSale;

/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 27-Sep-2011 12:00:00 AM
 */
public interface IBlCostSaleLogic {

	/**
	 * 
	 * @param blCostSale
	 * @throws Exception 
	 */
	public List<BlCostSale> saveBlCostSale(List<BlCostSale> blCostSale) throws Exception;

	/**
	 * 
	 * @param blCostSale
	 */
	public boolean deleteBlCostSale(BlCostSale blCostSale) throws Exception;

}